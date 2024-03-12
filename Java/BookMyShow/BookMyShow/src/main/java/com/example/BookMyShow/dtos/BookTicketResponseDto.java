package com.example.BookMyShow.dtos;

import com.example.BookMyShow.models.Ticket;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookTicketResponseDto {
    private String status;
    private Ticket ticket;
}
