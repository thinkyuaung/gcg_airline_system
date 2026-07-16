package com.example.Airline.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Airline.entity.Booking;
import com.example.Airline.service.BookingService;

@Controller
@RequestMapping("/airline")
public class BookingController {

	@Autowired
    private BookingService bookingService;



    @PostMapping("/booking")
    public String booking(
            @RequestParam int flightPlanId,
            @RequestParam int seatClassId,
            @RequestParam int passengers){


        Booking booking =
                bookingService.createBooking(
                        flightPlanId,
                        seatClassId,
                        passengers
                );


        return "redirect:/airline/payment/" + booking.getPayment().getPaymentId();

    }

}
