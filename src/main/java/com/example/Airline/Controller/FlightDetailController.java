package com.example.Airline.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Airline.entity.FlightPlan;
import com.example.Airline.service.FlightDetailService;

@Controller
@RequestMapping("/airline")
public class FlightDetailController {

	@Autowired
    private FlightDetailService flightDetailService;
	
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

	    model.addAttribute("flightPlan", flightPlan);
	    model.addAttribute("passengers", passengers);
	    model.addAttribute("seatClass", seatClass);
	    model.addAttribute("multiplier", multiplier);
	    model.addAttribute("totalPrice", totalPrice);

	    return "userview/flightDetail";
	}
}
