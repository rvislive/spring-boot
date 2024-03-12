package com.example.BookMyShow.services;


import com.example.BookMyShow.models.Show;
import com.example.BookMyShow.models.ShowSeat;
import com.example.BookMyShow.models.ShowSeatType;
import com.example.BookMyShow.repositories.ShowSeatTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketPriceCalculator {

    private ShowSeatTypeRepository showSeatTypeRepository;

    @Autowired
    public TicketPriceCalculator(ShowSeatTypeRepository showSeatTypeRepository) {
        this.showSeatTypeRepository = showSeatTypeRepository;
    }

    public float calculateTicketPrice(List<ShowSeat> showSeats) {
        Show show = showSeats.get(0).getShow();

        List<ShowSeatType> showSeatTypes = showSeatTypeRepository.findAllByShowEquals(show);

        float amount = 0;
        for(ShowSeat showSeat: showSeats) {
            for(ShowSeatType showSeatType: showSeatTypes) {
                if(showSeat.getSeat().getSeatType().equals(showSeatType.getSeatType())) {
                    amount += showSeatType.getPrice();
                }
            }
        }

        return amount;
    }
}
