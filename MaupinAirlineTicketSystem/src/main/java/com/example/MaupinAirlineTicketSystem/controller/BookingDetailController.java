package com.example.MaupinAirlineTicketSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.MaupinAirlineTicketSystem.entity.Booking;
import com.example.MaupinAirlineTicketSystem.entity.User;
import com.example.MaupinAirlineTicketSystem.repository.BookingRepository;
import com.example.MaupinAirlineTicketSystem.repository.UserRepository;

@Controller
@RequestMapping("/airline")
public class BookingDetailController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/booking/{id}")
    public String bookingDetail(
            @PathVariable int id,
            Model model) {

        Authentication auth =
                SecurityContextHolder.getContext().getAuthentication();

        User currentUser =
                userRepository.findByEmail(auth.getName());

        Booking booking =
                bookingRepository.findById(id)
                        .orElseThrow();

        // Security Check
        if (booking.getUser().getUserId() != currentUser.getUserId()) {

            return "redirect:/airline/history";
        }

        model.addAttribute("booking", booking);

        return "userview/bookingDetail";
    }

}