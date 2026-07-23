package com.example.MaupinAirlineTicketSystem.serviceImplementation;

import java.time.LocalDateTime;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MaupinAirlineTicketSystem.entity.Booking;
import com.example.MaupinAirlineTicketSystem.entity.FlightPlan;
import com.example.MaupinAirlineTicketSystem.entity.Payment;
import com.example.MaupinAirlineTicketSystem.entity.SeatClass;
import com.example.MaupinAirlineTicketSystem.entity.User;
import com.example.MaupinAirlineTicketSystem.repository.BookingRepository;
import com.example.MaupinAirlineTicketSystem.repository.FlightPlanRepository;
import com.example.MaupinAirlineTicketSystem.repository.PaymentRepository;
import com.example.MaupinAirlineTicketSystem.repository.SeatClassRepository;
import com.example.MaupinAirlineTicketSystem.repository.UserRepository;
import com.example.MaupinAirlineTicketSystem.service.BookingService;

import jakarta.transaction.Transactional;

@Service
public class BookingServiceImpl implements BookingService{

	@Autowired
    private BookingRepository bookingRepository;


    @Autowired
    private FlightPlanRepository flightPlanRepository;


    @Autowired
    private SeatClassRepository seatClassRepository;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PaymentRepository paymentRepository;
    
	@Override
	public double calculateTotalPrice(FlightPlan flightPlan, SeatClass seatClass, int passengers) {
		// TODO Auto-generated method stub
		return flightPlan.getPrice()
                *
                seatClass.getPriceMultiplier()
                *
                passengers;
	}


	@Override
	@Transactional
	public Booking createBooking(
	        int flightPlanId,
	        String seatClass,
	        int passengers,
	        User user) {


	    FlightPlan flightPlan =
	            flightPlanRepository
	            .findById(flightPlanId)
	            .orElseThrow();

	   // System.out.println("*****Seat Class Testing*****: "+seatClass);
	    
	    // Convert String -> SeatClass Entity

	    SeatClass seatClassEntity =
	            seatClassRepository
	            .findByClassName(seatClass);

	    //System.out.println("*****Seat Class*****: "+seatClassEntity.getClassName());

	    if(seatClassEntity == null){

	        throw new RuntimeException(
		            "Seat class not found"
		        );

	    }
	    
	    Booking booking = new Booking();
	    
	 


	    booking.setFlightPlan(flightPlan);

	    booking.setUser(user);

	    booking.setSeatClass(seatClassEntity);


	    booking.setBookingDate(
	            LocalDateTime.now()
	    );


	    booking.setStatus("PENDING");



	    double totalAmount =
	            flightPlan.getPrice()
	            * seatClassEntity.getPriceMultiplier()
	            * passengers;



	    booking.setTotalAmount(totalAmount);

	    Payment payment = new Payment();

	    payment.setAmount(totalAmount);

	    payment.setPaymentStatus("WAITING");

	    payment.setBooking(booking);


	    paymentRepository.save(payment);


	    booking.setPayment(payment);
	    
	    booking.setPassengers(passengers);
	    

	    return bookingRepository.save(booking);

	}
}
