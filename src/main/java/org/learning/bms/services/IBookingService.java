package org.learning.bms.services;

import org.learning.bms.models.Ticket;

import java.util.List;
import java.util.Optional;

public interface IBookingService {

    boolean blockSeats(long showId, List<Long> seatIds, long userId);

    Optional<Ticket> bookTicket(long showId, List<Long> showSeatIds, Long userId);

    void clearAllSeatLocks();
}
