package com.example.Airline.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.Airline.service.CancellationService;

@Controller
public class CancellationController {

	@Autowired
    private CancellationService cancellationService;

    @GetMapping("/airline/cancel/{bookingId}")
    public String cancelBooking(@PathVariable int bookingId,
    		 Model model) {

    	 try {

    	        cancellationService.cancelBooking(
    	            bookingId,
    	            "Cancelled by user"
    	        );

    	    } catch(RuntimeException e) {

    	        model.addAttribute(
    	            "error",
    	            e.getMessage()
    	        );

    	        return "cancelError";
    	    }


        return "redirect:/airline/history";
    }

}
