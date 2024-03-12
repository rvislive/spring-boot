package com.example.BookMyShow.services;

import com.example.BookMyShow.exceptions.ShowSeatNotAvailableException;
import com.example.BookMyShow.models.*;
import com.example.BookMyShow.repositories.ShowRepository;
import com.example.BookMyShow.repositories.ShowSeatRepository;
import com.example.BookMyShow.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TicketService {

    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private TicketRepository ticketRepository;
    private TicketPriceCalculator ticketPriceCalculator;

    @Autowired
    public TicketService(ShowRepository showRepository, ShowSeatRepository showSeatRepository, TicketRepository ticketRepository, TicketPriceCalculator ticketPriceCalculator) {
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.ticketRepository = ticketRepository;
        this.ticketPriceCalculator = ticketPriceCalculator;
    }

    public Ticket bookTicket(Long showId, List<Long> showSeatIds) throws ShowSeatNotAvailableException {

        // 1. Get show with that ID
        Show show = showRepository.findByIdEquals(showId);

        // 2. Get ShowSeat with those IDs
        List<ShowSeat> showSeats = showSeatRepository.findAllByIdIn(showSeatIds);

        // 3. Check if all seats are available

        for(ShowSeat showSeat: showSeats) {
            if(!showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)) {
                throw new ShowSeatNotAvailableException("Show Seat " + showSeat.getSeat() + " is not available for booking.");
            }
        }

        // 4.1 If available: set status to locked
        for(ShowSeat showSeat: showSeats) {
            showSeat.setShowSeatStatus(ShowSeatStatus.LOCKED);
            showSeatRepository.save(showSeat);
        }

        // 4.2 create a ticket and save to DB
        Ticket ticket = new Ticket();
        ticket.setPrice(ticketPriceCalculator.calculateTicketPrice(showSeats));
        ticket.setTicketStatus(TicketStatus.IN_PROCESS);
        ticket.setBookingTime(new Date());
        ticket.setShow(show);
        ticket.setShowSeats(showSeats);

        // 4.2 If any not available:
        //     throw an exception

        Ticket savedTicket = ticketRepository.save(ticket);
        return savedTicket;
    }
}
