package org.learning.bms.services;

import org.learning.bms.models.Show;
import org.learning.bms.models.ShowSeat;
import org.learning.bms.models.ShowSeatStatus;
import org.learning.bms.models.Ticket;
import org.learning.bms.models.TicketStatus;
import org.learning.bms.models.User;
import org.learning.bms.repositories.ShowRepository;
import org.learning.bms.repositories.ShowSeatRepository;
import org.learning.bms.repositories.TicketRepository;
import org.learning.bms.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RedisBookingService implements IBookingService{

    private final ICacheService cacheService;
    private final ShowSeatRepository showSeatRepository;
    private final TicketRepository ticketRepository;
    private final ShowRepository showRepository;
    private final UserRepository userRepository;

    public RedisBookingService(ICacheService cacheService,
                               ShowSeatRepository showSeatRepository,
                               TicketRepository ticketRepository,
                               ShowRepository showRepository,
                               UserRepository userRepository) {
        this.cacheService = cacheService;
        this.showSeatRepository = showSeatRepository;
        this.ticketRepository = ticketRepository;
        this.showRepository = showRepository;
        this.userRepository = userRepository;
    }


    @Override
    public boolean blockSeats(long showId, List<Long> seatIds, long userId) {
        /*
            when user selects particular seats...then we keep those seat as locked in redis for specific time(ttl)
            then user booked within ttl..we will update it as booked in db otherwise after ttl time,we will remove those seats
            from redis so that those seats are available for other users.
         */

        System.out.println("before updating:: redis cache");
        cacheService.getAllKeysAndValues();

        //check db whether given seats available or not...since final booked seats are present in db

        List<ShowSeat> showSeats = showSeatRepository.findAllByShowIdAndSeatIdIn(showId,seatIds);
        for(ShowSeat showSeat : showSeats) {
            if(showSeat.getStatus().equals(ShowSeatStatus.BOOKED)) {
                return false;
            }
        }

        //since locked seats will be present in redis...here also
        //we need to check whether all seats available or not

        for (ShowSeat showSeat : showSeats) {

            String seatKey = "seatId=" + showSeat.getId();
            String seatStatus = (String) cacheService.get(seatKey);

            if(seatStatus != null) {
                return false;
            }
        }

        //if all given seats are available in db and redis....then user can lock those seats
        //we are locked the seats by keeping it redis
        for (ShowSeat showSeat : showSeats) {

            String seatKey = "seatId=" + showSeat.getId();
            cacheService.set(seatKey,ShowSeatStatus.LOCKED);
        }

        System.out.println("after updating:: redis cache");
        cacheService.getAllKeysAndValues();

        return true;
    }

    @Transactional
    @Override
    public Optional<Ticket> bookTicket(long showId, List<Long> showSeatIds, Long userId) {

        //while booking seats...we are checking whether those seats are blocked in redis or not
        // the seats which present in redis...those seats only can be booked;

        for (Long showSeatId : showSeatIds) {

            String seatKey = "seatId=" + showSeatId;
            String seatStatus = (String) cacheService.get(seatKey);

            if(seatStatus == null) {
                return Optional.empty();
            }
        }

        System.out.println("all tickets are available");
        User user = userRepository.findById(userId).get();
        Show show = showRepository.findById(showId).get();

        Ticket ticket = createTicketAndBookSeats(show,user,showSeatIds);

        return Optional.ofNullable(ticket);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Ticket createTicketAndBookSeats(Show show, User user, List<Long> showSeatIds) {


        Ticket ticket = new Ticket();
        ticket.setAmount(100);
        ticket.setUser(user);
        ticket.setShow(show);
        ticket.setStatus(TicketStatus.BOOKED);

        ticket = ticketRepository.save(ticket);

        showSeatRepository.bookShowSeats(showSeatIds,ticket);
        //we can remove these seats from redis cache...otherwise if already set the ttl
        // it will be removed from redis after ttl...
        //TODO :: remove logic can be added later

        return ticket;
    }

    @Override
    public void clearAllSeatLocks() {
        cacheService.deleteAll();;
    }
}
