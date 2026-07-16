package com.example.Airline.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
            Model model){


        FlightPlan flightPlan =
                flightDetailService.getFlightPlan(id);



        model.addAttribute(
                "flightPlan",
                flightPlan
        );


        model.addAttribute(
                "features",
                flightDetailService.getFeatures(
                    flightPlan.getFlight().getFlightId()
                )
        );


        model.addAttribute(
                "seatClasses",
                flightDetailService.getSeatClasses()
        );


        return "flightDetail";

    }
}
