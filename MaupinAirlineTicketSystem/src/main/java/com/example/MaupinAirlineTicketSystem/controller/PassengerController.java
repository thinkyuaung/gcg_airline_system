package com.example.MaupinAirlineTicketSystem.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.MaupinAirlineTicketSystem.entity.Booking;
import com.example.MaupinAirlineTicketSystem.entity.User;
import com.example.MaupinAirlineTicketSystem.repository.BookingRepository;
import com.example.MaupinAirlineTicketSystem.repository.PaymentRepository;
import com.example.MaupinAirlineTicketSystem.repository.UserRepository;
import com.example.MaupinAirlineTicketSystem.entity.BookingDetail;
import com.example.MaupinAirlineTicketSystem.repository.BookingDetailRepository;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/airline")
public class PassengerController {

	 @Autowired
	    private BookingRepository bookingRepository;


	    @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private BookingDetailRepository bookingDetailRepository;

	    @Autowired
	    private PaymentRepository paymentRepository;
	    
	    @GetMapping("/passenger/{bookingId}")
	    public String passengerForm(
	            @PathVariable int bookingId,
	            Model model,
	            Principal principal){


	        Booking booking =
	                bookingRepository.findById(bookingId)
	                .orElseThrow();



	        User user =
	                booking.getUser();



	        model.addAttribute("booking", booking);

	        model.addAttribute("user", user);


	        return "userview/passengerForm";

	    }
	    
	    @PostMapping("/passenger/save")
	    public String savePassenger(
	            @RequestParam int bookingId,
	            @RequestParam List<String> firstName,
	            @RequestParam List<String> lastName,
	            @RequestParam List<String> passport,
	            @RequestParam List<LocalDate> dob
	    ){

	        Booking booking =
	                bookingRepository.findById(bookingId)
	                .orElseThrow();


	        for(int i = 0; i < firstName.size(); i++){

	            BookingDetail detail = new BookingDetail();

	            detail.setBooking(booking);

	            detail.setPassengerFirstName(
	                    firstName.get(i)
	            );

	            detail.setPassengerLastName(
	                    lastName.get(i)
	            );

	            detail.setPassport(
	                    passport.get(i)
	            );

	            detail.setDOB(
	                    dob.get(i)
	            );


	            bookingDetailRepository.save(detail);

	        }


	        return "redirect:/airline/payment/"
	                + booking.getPayment().getPaymentId();
	    }
}
