package com.example.MaupinAirlineTicketSystem.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.MaupinAirlineTicketSystem.entity.FlightPlan;
import com.example.MaupinAirlineTicketSystem.entity.Promotion;
import com.example.MaupinAirlineTicketSystem.entity.SeatClass;
import com.example.MaupinAirlineTicketSystem.repository.SeatClassRepository;
import com.example.MaupinAirlineTicketSystem.repository.UserPromotionRepository;
import com.example.MaupinAirlineTicketSystem.service.FlightDetailService;

@Controller
@RequestMapping("/airline")
public class FlightDetailController {

	@Autowired
    private FlightDetailService flightDetailService;

	@Autowired
	private UserPromotionRepository promotionRepository;
	
	@Autowired SeatClassRepository seatRepo;

	@GetMapping("/flight/{id}")
	public String flightDetail(
	        @PathVariable int id,
	        @RequestParam int passengers,
	        @RequestParam String seatClass,
	        Model model) {

		System.out.println("Passengers = " + passengers);
		System.out.println("Seat Class = " + seatClass);

	    FlightPlan flightPlan =
	            flightDetailService.getFlightPlan(id);
	    

	    //Seat Class Multiplier
	   SeatClass seat =  seatRepo.findByClassNameIgnoreCase(seatClass);
	   double multiplier = 1;
	   
	    if (seat != null) {
	        multiplier = seat.getPriceMultiplier();
	    }
	    
	  //  double multiplier = 1;
//
//	    if(seatClass.equalsIgnoreCase("Business")){
//	    	
//	        multiplier = 1.5;
//	    }
//	    else if(seatClass.equalsIgnoreCase("First Class")){
//	        multiplier = 2;
//	    }

	    double totalPrice =
	            flightPlan.getPrice()
	            * multiplier
	            * passengers;

	    Optional<Promotion> globalPromotion =
	            promotionRepository
	            .findFirstByStatusAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
	                    "Active",
	                    LocalDateTime.now(),
	                    LocalDateTime.now()
	            );

	    Promotion flightPlanPromotion = flightPlan.getPromotion();

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
	    	totalPrice = totalPrice - (totalPrice * totalDiscountPercent / 100);
	    }

	    model.addAttribute("flightPlan", flightPlan);
	    model.addAttribute("passengers", passengers);
	    model.addAttribute("seatClass", seatClass);
	    model.addAttribute("multiplier", multiplier);
	    model.addAttribute("totalPrice", totalPrice);

	    return "userview/flightDetail";
	}
}
