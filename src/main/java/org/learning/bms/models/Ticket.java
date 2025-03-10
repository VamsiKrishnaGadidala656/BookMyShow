package org.learning.bms.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Ticket extends BaseModel{

    private int amount;

    @ManyToOne
    private User user;

    @ManyToOne
    private Show show;

    @ManyToOne
    private ShowSeat showSeat;

    @Enumerated(EnumType.ORDINAL)
    private TicketStatus ticketStatus;

}
