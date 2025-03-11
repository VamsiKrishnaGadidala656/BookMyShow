package org.learning.bms.repositories;

import org.learning.bms.models.Show;
import org.learning.bms.models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat,Long> {

    List<ShowSeat> findAllByShow_Id(long showId);
}

