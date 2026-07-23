package com.example.MaupinAirlineTicketSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.MaupinAirlineTicketSystem.service.ReviewService;

@Controller
@RequestMapping("/airline")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	@PostMapping("/review")
	
	public String savereview(
			
			@RequestParam int bookingId,
			@RequestParam int rating,
			@RequestParam String comment
			) {
		
		reviewService.saveReview(bookingId, rating, comment);
		
		return "redirect:/airline/";
	}
	
}
