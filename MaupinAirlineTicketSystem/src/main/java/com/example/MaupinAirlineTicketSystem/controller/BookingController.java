package com.example.MaupinAirlineTicketSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.MaupinAirlineTicketSystem.entity.Booking;
import com.example.MaupinAirlineTicketSystem.entity.User;
import com.example.MaupinAirlineTicketSystem.repository.UserRepository;
import com.example.MaupinAirlineTicketSystem.service.BookingService;

@Controller
@RequestMapping("/airline")
public class BookingController {

	@Autowired
    private BookingService bookingService;

	@Autowired
	private UserRepository userRepository;

    @PostMapping("/booking")
    public String booking(
            @RequestParam int flightPlanId,
            @RequestParam int passengers,

            @RequestParam String seatClass){

    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User user = userRepository.findByEmail(auth.getName());

        Booking booking =
                bookingService.createBooking(
                        flightPlanId,
                        seatClass,
                        passengers,
                        user
                );

        return "redirect:/airline/payment/" + booking.getPayment().getPaymentId();

    }

}
