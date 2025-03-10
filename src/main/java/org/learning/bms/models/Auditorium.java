package org.learning.bms.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Auditorium extends BaseModel{

    private String name;

    @ManyToOne
    private Theatre theatre;

    @OneToMany
    private List<Seat> seats;

    @OneToMany
    private List<Show> shows;
}
