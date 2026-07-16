package com.example.Airline.serviceImpl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Airline.entity.Booking;
import com.example.Airline.entity.FlightPlan;
import com.example.Airline.entity.Payment;
import com.example.Airline.entity.SeatClass;
import com.example.Airline.entity.User;
import com.example.Airline.repository.BookingRepository;
import com.example.Airline.repository.FlightPlanRepository;
import com.example.Airline.repository.PaymentRepository;
import com.example.Airline.repository.SeatClassRepository;
import com.example.Airline.repository.UserRepository;
import com.example.Airline.service.BookingService;

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
	        int passengers) {


	    FlightPlan flightPlan =
	            flightPlanRepository
	            .findById(flightPlanId)
	            .orElseThrow();


	    // Convert String -> SeatClass Entity

	    SeatClass seatClassEntity =
	            seatClassRepository
	            .findByClassName(seatClass);



	    if(seatClassEntity == null){

	        throw new RuntimeException(
	            "Seat class not found"
	        );

	    }



	    Booking booking = new Booking();


	    booking.setFlightPlan(flightPlan);


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
	    

	    return bookingRepository.save(booking);

	}
}
