package com.example.MaupinAirlineTicketSystem.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.MaupinAirlineTicketSystem.entity.Booking;
import com.example.MaupinAirlineTicketSystem.entity.User;
import com.example.MaupinAirlineTicketSystem.repository.UserRepository;
import com.example.MaupinAirlineTicketSystem.service.BookingService;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/airline")
public class BookingController {

	@Autowired
    private BookingService bookingService;

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/booking/reserve")
	public String reserve(
			@RequestParam int flightPlanId,
			@RequestParam int passengers,
			@RequestParam String seatClass,
			HttpSession session) {

		session.setAttribute("pendingFlightPlanId", flightPlanId);
		session.setAttribute("pendingPassengers", passengers);
		session.setAttribute("pendingSeatClass", seatClass);

		if (session.getAttribute("loginUserName") == null) {
			return "redirect:/airline/login";
		}

		return "redirect:/airline/booking/complete";
	}

	@GetMapping("/booking/complete")
	public String completeBooking(HttpSession session, RedirectAttributes redirectAttributes) {

	    Integer flightPlanId = (Integer) session.getAttribute("pendingFlightPlanId");
	    Integer passengers = (Integer) session.getAttribute("pendingPassengers");
	    String seatClass = (String) session.getAttribute("pendingSeatClass");

	    if (flightPlanId == null) {
	        return "redirect:/airline/flights";
	    }

	    session.removeAttribute("pendingFlightPlanId");
	    session.removeAttribute("pendingPassengers");
	    session.removeAttribute("pendingSeatClass");

	    try {
		    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    User user = userRepository.findByEmail(auth.getName());

		    Booking booking = bookingService.createBooking(
		            flightPlanId,
		            seatClass,
		            passengers,
		            user
		    );

		    return "redirect:/airline/passenger/" + booking.getBookingId();
	    } catch (RuntimeException e) {
	        redirectAttributes.addFlashAttribute("bookingError", e.getMessage());
	        return redirectToBookingError(redirectAttributes, flightPlanId, passengers, seatClass);
	    }
	}

    private String redirectToBookingError(RedirectAttributes redirectAttributes,
            int flightPlanId, int passengers, String seatClass) {
        redirectAttributes.addAttribute("passengers", passengers);
        redirectAttributes.addAttribute("seatClass", seatClass);
        return "redirect:/airline/flight/" + flightPlanId;
    }

    @PostMapping("/booking")
    public String booking(
            @RequestParam int flightPlanId,
            @RequestParam int passengers,
            @RequestParam String seatClass,
            Principal principal,
            RedirectAttributes redirectAttributes
           ){

    	try {
	    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    	User user = userRepository.findByEmail(auth.getName());

	        Booking booking =
	                bookingService.createBooking(
	                        flightPlanId,
	                        seatClass,
	                        passengers,
	                        user
	                );

	        return "redirect:/airline/passenger/" + booking.getBookingId();
    	} catch (RuntimeException e) {
    		redirectAttributes.addFlashAttribute("bookingError", e.getMessage());
    		return redirectToBookingError(redirectAttributes, flightPlanId, passengers, seatClass);
    	}

    }

}
