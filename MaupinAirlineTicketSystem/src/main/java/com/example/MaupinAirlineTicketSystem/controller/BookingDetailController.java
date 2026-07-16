package com.example.MaupinAirlineTicketSystem.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import com.example.MaupinAirlineTicketSystem.entity.Booking;
import com.example.MaupinAirlineTicketSystem.repository.BookingRepository;



@Controller
@RequestMapping("/airline")
public class BookingDetailController {


@Autowired
private BookingRepository bookingRepository;



@GetMapping("/booking/{id}")
public String bookingDetail(
        @PathVariable int id,
        Model model){


    Booking booking =
            bookingRepository.findById(id)
            .orElseThrow();



    model.addAttribute(
            "booking",
            booking
    );


    return "userView/bookingDetail";

}


}