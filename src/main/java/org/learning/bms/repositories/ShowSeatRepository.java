package org.learning.bms.repositories;

import org.learning.bms.models.Show;
import org.learning.bms.models.ShowSeat;
import org.learning.bms.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat,Long> {

    List<ShowSeat> findAllByShowId(Long showId);
    List<ShowSeat> findAllByShowIdAndSeatIdIn(Long showId, List<Long> seatIds);

    @Modifying
    @Query("UPDATE ShowSeat s SET s.status = 1, s.ticket = :ticket where s.id IN :ids")
    int bookShowSeats(@Param("ids") List<Long> ids, @Param("ticket") Ticket ticket);

}

