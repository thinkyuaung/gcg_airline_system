package com.example.MaupinAirlineTicketSystem.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.MaupinAirlineTicketSystem.repository.BookingRepository;
import com.example.MaupinAirlineTicketSystem.repository.UserPromotionRepository;
import com.example.MaupinAirlineTicketSystem.entity.Booking;
import com.example.MaupinAirlineTicketSystem.entity.Payment;
import com.example.MaupinAirlineTicketSystem.entity.Promotion;
import com.example.MaupinAirlineTicketSystem.service.PaymentService;
import com.example.MaupinAirlineTicketSystem.service.ReviewService;

@Controller
@RequestMapping("/airline")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private UserPromotionRepository promotionRepository;

	@GetMapping("/payment/{id}")
	public String paymentPage(
	        @PathVariable int id,
	        Model model){

	    Payment payment = paymentService.getPayment(id);

	    model.addAttribute("payment", payment);

	    Booking booking = payment.getBooking();

	    double originalPrice =
	            booking.getFlightPlan().getPrice()
	            *
	            booking.getSeatClass().getPriceMultiplier()
	            *
	            booking.getPassengers();
	    
	    System.out.println("**********Price:"+booking.getFlightPlan().getPrice());
	    System.out.println("Seat:"+booking.getSeatClass().getPriceMultiplier());
	    System.out.println("Passengers:"+booking.getPassengers());
	    System.out.println("Original Price:"+originalPrice);

	    model.addAttribute("originalPrice", originalPrice);

	    Optional<Promotion> globalPromotion =
	            promotionRepository
	            .findFirstByStatusAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
	                    "Active",
	                    LocalDateTime.now(),
	                    LocalDateTime.now()
	            );

	    Promotion flightPlanPromotion = booking.getFlightPlan().getPromotion();

//	    if (flightPlanPromotion != null) {
//	    	model.addAttribute("flightPlanPromotion", flightPlanPromotion);
//	    }
//
//	    if (globalPromotion.isPresent()) {
//	    	model.addAttribute("globalPromotion", globalPromotion.get());
//	    }
	    
	    double totalDiscountPercent = 0;

	    if (flightPlanPromotion != null) {
	    	totalDiscountPercent += flightPlanPromotion.getPercentage();
	    	model.addAttribute("flightPlanPromotion", flightPlanPromotion);
	    }

	    if (globalPromotion.isPresent()) {
	    	Promotion gp = globalPromotion.get();
	    	totalDiscountPercent += gp.getPercentage();
	    	model.addAttribute("globalPromotion", gp);
	    }

	    if (totalDiscountPercent > 0) {
	    	originalPrice = originalPrice - (originalPrice * totalDiscountPercent / 100);
	    }

	    model.addAttribute("finalPrice", originalPrice);
//	    booking.getFlightPlan().setPrice(originalPrice);

	    return "userview/payment";
	}

	@GetMapping("/paymentSuccess")
	public String paymentSuccess(
	        @RequestParam int bookingId,
	        @RequestParam(required = false) String success,
	        Model model) {


	    Booking booking = bookingRepository
	            .findById(bookingId)
	            .orElseThrow();


	    boolean reviewed = reviewService.hasReview(bookingId);

	    boolean isSuccess = success != null;


	    model.addAttribute("booking", booking);
	    model.addAttribute("reviewed", reviewed);
	    model.addAttribute("success", isSuccess);


	    return "userview/paymentSuccess";
	}


	@PostMapping("/payment/upload")
	public String uploadPayment(
	        @RequestParam int paymentId,
	        @RequestParam MultipartFile screenshot,
	        Model model){

		Payment payment =
		        paymentService.uploadScreenshot(paymentId, screenshot);

		
		model.addAttribute(
		        "payment",
		        payment
		    );


		return "redirect:/airline/paymentSuccess?bookingId="
        + payment.getBooking().getBookingId();

	}
}
