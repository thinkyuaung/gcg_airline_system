package com.example.MaupinAirlineTicketSystem.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.example.MaupinAirlineTicketSystem.entity.Booking;
import com.example.MaupinAirlineTicketSystem.entity.User;
import com.example.MaupinAirlineTicketSystem.repository.BookingRepository;
import com.example.MaupinAirlineTicketSystem.repository.UserRepository;

@Controller
@RequestMapping("/airline")
public class DashboardController {

	@Autowired
    private UserRepository userRepository;


    @Autowired
    private BookingRepository bookingRepository;
    
    @GetMapping("/dashboard")
    public String dashboard(Model model, Authentication authentication){

    	System.out.println("USER: " + authentication.getName());
        System.out.println("ROLE: " + authentication.getAuthorities());
    	
    	Authentication auth =
    	        SecurityContextHolder
    	        .getContext()
    	        .getAuthentication();


    	User user =
    	        userRepository.findByEmail(auth.getName());


    	List<Booking> bookings =
    	        bookingRepository.findByUser(user);


        long totalBookings =
                bookings.size();



        long cancelledBookings =
                bookings.stream()
                .filter(b -> 
                    "CANCELLED".equals(b.getStatus()))
                .count();

        long upcomingFlightCount =
                bookings.stream()
                .filter(b ->
                    "CONFIRMED".equals(b.getStatus()))
                .filter(b ->
                    b.getFlightPlan()
                     .getDepartureTime()
                     .isAfter(LocalDateTime.now()))
                .count();

        Booking upcomingFlight =
                bookings.stream()
                .filter(b -> "CONFIRMED".equals(b.getStatus()))
                .filter(b ->
                    b.getFlightPlan()
                     .getDepartureTime()
                     .isAfter(LocalDateTime.now()))
                .findFirst()
                .orElse(null);

        model.addAttribute(
        	    "username",
        	    user.getFirstName() + " " + user.getLastName()
        	);


        model.addAttribute(
                "totalBookings",
                totalBookings
        );



        model.addAttribute(
                "cancelledBookings",
                cancelledBookings
        );



        model.addAttribute(
                "upcomingFlightCount",
                upcomingFlightCount
        );

        model.addAttribute(
                "upcomingFlight",
                upcomingFlight
        );



        model.addAttribute(
                "recentBookings",
                bookings
        );

        model.addAttribute("currentPage", "dashboard");

        return "userview/dashboard";

    }


}
