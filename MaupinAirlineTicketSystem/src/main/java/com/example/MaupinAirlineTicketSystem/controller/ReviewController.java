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
import com.example.MaupinAirlineTicketSystem.service.ReviewService;

@Controller
@RequestMapping("/airline")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@GetMapping("/paymentSuccess")
	public String paymentSuccess(
	        @RequestParam int bookingId,
	        Model model) {

	    Booking booking = bookingRepository.findById(bookingId)
	            .orElseThrow();

	    model.addAttribute("booking", booking);

	    return "userview/paymentSuccess";
	}
	
	@PostMapping("/review")
	
	public String savereview(
			
			@RequestParam int bookingId,
			@RequestParam(required = false, defaultValue = "0") int rating,
			@RequestParam String comment
			) {
		
		 if(rating == 0){
		        return "redirect:/airline/paymentSuccess?error=rating";
		    }

		    reviewService.saveReview(bookingId, rating, comment);

		    return "redirect:/airline/paymentSuccess?bookingId=" + bookingId + "&success=true";
	}
}
