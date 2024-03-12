package com.example.BookMyShow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Seat extends BaseModel {

    @ManyToOne
    private SeatType seatType;
    private String name;
    private int row;
    private int col;
}
