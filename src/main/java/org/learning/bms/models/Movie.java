package org.learning.bms.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Movie extends BaseModel{

    private String name;
    private String poster;


    @OneToMany
    private List<Show> shows;
}
