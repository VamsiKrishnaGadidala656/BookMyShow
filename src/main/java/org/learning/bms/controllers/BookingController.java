package org.learning.bms.controllers;

import org.learning.bms.dtos.BlockSeatRequestDTO;
import org.learning.bms.dtos.BookSeatRequestDTO;
import org.learning.bms.models.Ticket;
import org.learning.bms.services.IBookingService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {

    protected IBookingService bookingService;

    BookingController(IBookingService bookingService){
        this.bookingService = bookingService;
    }

    @PostMapping("/block")
    public boolean block(@RequestBody BlockSeatRequestDTO blockSeatRequestDTO){

        return bookingService.blockSeats(
                blockSeatRequestDTO.getShowId(),
                blockSeatRequestDTO.getSeatIds(),
                blockSeatRequestDTO.getUserId());

    }

    @DeleteMapping
    public void clearAllSeatLocks() {
        bookingService.clearAllSeatLocks();
    }

    @PostMapping("/confirm")
    private Optional<Ticket> confirmBooking(@RequestBody BookSeatRequestDTO bookSeatRequestDTO) {

        return bookingService.bookTicket(bookSeatRequestDTO.getShowId(),
                bookSeatRequestDTO.getShowSeatIds(),
                bookSeatRequestDTO.getUserId());
    }
}
