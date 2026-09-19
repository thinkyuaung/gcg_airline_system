package com.example.MaupinAirlineTicketSystem.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.MaupinAirlineTicketSystem.entity.Booking;
import com.example.MaupinAirlineTicketSystem.entity.User;
import com.example.MaupinAirlineTicketSystem.repository.BookingRepository;
import com.example.MaupinAirlineTicketSystem.repository.UserRepository;
import com.example.MaupinAirlineTicketSystem.service.CancellationService;



@Controller
@RequestMapping("/airline")
public class CancellationController {


@Autowired
private BookingRepository bookingRepository;


@Autowired
private CancellationService cancellationService;

@Autowired
private UserRepository userRepository;


@GetMapping("/cancel/confirm/{id}")
public String confirmPage(
        @PathVariable int id,
        Model model){


	Authentication auth =
	        SecurityContextHolder.getContext().getAuthentication();

	User user =
	        userRepository.findByEmail(auth.getName());

	Booking booking =
	        bookingRepository.findById(id)
	        .orElseThrow();

	if (booking.getUser().getUserId() != user.getUserId()) {
	    return "redirect:/airline/history";
	}
    model.addAttribute(
            "booking",
            booking
    );


    return "userview/cancelConfirm";

}




@PostMapping("/cancel/{id}")
public String cancel(
        @PathVariable int id,
        @RequestParam String reason,
        Model model){

    try {
        Booking booking = bookingRepository.findById(id).orElseThrow();

        if ("CANCELLED".equals(booking.getStatus())
                || "Pending Cancel".equals(booking.getStatus())) {
            throw new RuntimeException("This booking has already been cancelled.");
        }

        booking.setStatus("Pending Cancel");
        booking.setCancelDescription(reason);
        bookingRepository.save(booking);

        return "userview/cancelSuccess";

    } catch(RuntimeException e){

        model.addAttribute("message", e.getMessage());
        return "userview/cancelError";
    }

}

}