package com.example.BookMyShow.controllers;

import com.example.BookMyShow.dtos.BookTicketRequestDto;
import com.example.BookMyShow.dtos.BookTicketResponseDto;
import com.example.BookMyShow.exceptions.ShowSeatNotAvailableException;
import com.example.BookMyShow.models.Ticket;
import com.example.BookMyShow.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TicketController {
    private TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public BookTicketResponseDto bookTicket(BookTicketRequestDto request) {
        try {
            Long showId = request.getShowId();
            List<Long> showSeatIds = request.getShowSeatIds();

            Ticket ticket = ticketService.bookTicket(
                    showId,
                    showSeatIds
            );

            BookTicketResponseDto response = new BookTicketResponseDto();
            response.setStatus("SUCCESS");
            response.setTicket(ticket);

            return response;
        } catch (ShowSeatNotAvailableException exception) {
            BookTicketResponseDto response = new BookTicketResponseDto();
            response.setStatus("FAILURE");
            return response;
        }
    }


}
