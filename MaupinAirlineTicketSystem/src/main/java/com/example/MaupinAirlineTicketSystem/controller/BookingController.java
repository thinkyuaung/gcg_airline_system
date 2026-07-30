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

		return "redirect:/airline/login";
	}

	@GetMapping("/booking/complete")
	public String completeBooking(HttpSession session) {

	    Integer flightPlanId = (Integer) session.getAttribute("pendingFlightPlanId");
	    Integer passengers = (Integer) session.getAttribute("pendingPassengers");
	    String seatClass = (String) session.getAttribute("pendingSeatClass");

	    if (flightPlanId == null) {
	        return "redirect:/airline/flights";
	    }

	    session.removeAttribute("pendingFlightPlanId");
	    session.removeAttribute("pendingPassengers");
	    session.removeAttribute("pendingSeatClass");

	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    User user = userRepository.findByEmail(auth.getName());

	    Booking booking = bookingService.createBooking(
	            flightPlanId,
	            seatClass,
	            passengers,
	            user
	    );

	    return "redirect:/airline/passenger/" + booking.getBookingId();
	}

    @PostMapping("/booking")
    public String booking(
            @RequestParam int flightPlanId,
            @RequestParam int passengers,
            @RequestParam String seatClass,
            Principal principal
           ){

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

    }

}
