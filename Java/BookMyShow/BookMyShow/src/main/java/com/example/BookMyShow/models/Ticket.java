package com.example.BookMyShow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Ticket extends BaseModel {
    private float price;

    @ManyToOne
    private Show show;

    @OneToMany
    private List<Payment> payments;

    // T:SS
    @ManyToMany // one tickets can have multiple tickets.
    private List<ShowSeat> showSeats;
    private Date bookingTime;

    @Enumerated(EnumType.ORDINAL)
    private TicketStatus ticketStatus;
}
