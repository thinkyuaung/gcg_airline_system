package com.example.MaupinAirlineTicketSystem.controller;

import java.time.LocalDateTime;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.MaupinAirlineTicketSystem.entity.Booking;
import com.example.MaupinAirlineTicketSystem.repository.BookingRepository;
import com.example.MaupinAirlineTicketSystem.service.BookingHistoryService;

@Controller
@RequestMapping("/airline")
public class BookingHistoryController {

    @Autowired
    private BookingHistoryService bookingHistoryService;

    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping("/history")
    public String allBookings(Model model) {

        model.addAttribute(
                "bookings",
                bookingHistoryService.getAllBookings());

        model.addAttribute("currentPage", "all");

        return "userview/bookingHistory";
    }


    @GetMapping("/history/upcoming")
    public String upcoming(Model model) {

        model.addAttribute(
                "bookings",
                bookingHistoryService.getUpcomingBookings());

        model.addAttribute("currentPage", "upcoming");

        return "userview/bookingHistory";
    }


    @GetMapping("/history/cancelled")
    public String cancelled(Model model) {

        model.addAttribute(
                "bookings",
                bookingHistoryService.getCancelledBookings());

        model.addAttribute("currentPage", "cancelled");

        return "userview/bookingHistory";
    }

}
