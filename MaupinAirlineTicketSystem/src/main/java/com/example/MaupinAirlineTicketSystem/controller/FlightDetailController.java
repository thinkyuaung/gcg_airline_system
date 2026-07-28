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
import com.example.MaupinAirlineTicketSystem.repository.UserPromotionRepository;
import com.example.MaupinAirlineTicketSystem.service.FlightDetailService;

@Controller
@RequestMapping("/airline")
public class FlightDetailController {

	@Autowired
    private FlightDetailService flightDetailService;
	
	@Autowired
	private UserPromotionRepository promotionRepository;
	
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

	    double multiplier = 1;

	    if(seatClass.equalsIgnoreCase("Business")){
	        multiplier = 1.5;
	    }
	    else if(seatClass.equalsIgnoreCase("First")){
	        multiplier = 2;
	    }

	    double totalPrice =
	            flightPlan.getPrice()
	            * multiplier
	            * passengers;
	    
	    Optional<Promotion> promotion =
	            promotionRepository
	            .findFirstByStatusAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
	                    "Active",
	                    LocalDateTime.now(),
	                    LocalDateTime.now()
	            );


	    if(promotion.isPresent()){

	        Promotion p = promotion.get();

	        totalPrice =
	            totalPrice - (totalPrice * p.getPercentage() / 100);

	        model.addAttribute("promotion", p);

	    }

	    model.addAttribute("flightPlan", flightPlan);
	    model.addAttribute("passengers", passengers);
	    model.addAttribute("seatClass", seatClass);
	    model.addAttribute("multiplier", multiplier);
	    model.addAttribute("totalPrice", totalPrice);
	    model.addAttribute("totalPrice", totalPrice);
	    
	    return "userview/flightDetail";
	}
}
