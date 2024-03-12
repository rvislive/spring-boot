package com.example.BookMyShow.dtos;

import com.example.BookMyShow.models.ShowSeat;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookTicketRequestDto {
    private Long showId;
    private List<Long> showSeatIds;
}
