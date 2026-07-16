package com.example.Airline.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Airline.service.BookingHistoryService;

@Controller
@RequestMapping("/airline")
public class BookingHistoryController {

	@Autowired
	private BookingHistoryService bookingHistoryService;



	@GetMapping("/history")
	public String history(Model model){


	    // fixed user
	    int userId = 1;


	    model.addAttribute(
	            "bookings",
	            bookingHistoryService
	            .getBookingHistory(userId)
	    );


	    return "bookingHistory";

	}


}
