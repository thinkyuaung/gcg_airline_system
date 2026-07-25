package com.example.MaupinAirlineTicketSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.MaupinAirlineTicketSystem.entity.Booking;
import com.example.MaupinAirlineTicketSystem.repository.BookingRepository;
import com.example.MaupinAirlineTicketSystem.repository.ReviewRepository;
import com.example.MaupinAirlineTicketSystem.service.ReviewService;

@Controller
@RequestMapping("/airline")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@PostMapping("/review")
	public String saveReview(
			
			@RequestParam("bookingId") Integer bookingId,
			@RequestParam(defaultValue="0") int rating,
	        @RequestParam("comment") String comment
	){
		
			if(bookingId == null){
				throw new RuntimeException("Booking ID missing");
	    }
	    
	    reviewService.saveReview(
	            bookingId,
	            rating,
	            comment
	    );

	   
	    return "redirect:/airline/paymentSuccess?bookingId="
	            + bookingId
	            + "&success=true";

	}
}
