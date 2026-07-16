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
	public Booking createBooking(int flightPlanId, int seatClassId, int passengers) {
		// TODO Auto-generated method stub
		
		User user =
                userRepository.findById(1)
                .orElseThrow();



        FlightPlan flightPlan =
                flightPlanRepository.findById(flightPlanId)
                .orElseThrow();



        SeatClass seatClass =
                seatClassRepository.findById(seatClassId)
                .orElseThrow();



        double total =
                calculateTotalPrice(
                        flightPlan,
                        seatClass,
                        passengers
                );



        Booking booking = new Booking();


        booking.setBookingCode(
                "BK-" + UUID.randomUUID()
                .toString()
                .substring(0,8)
        );


        booking.setBookingDate(
                LocalDateTime.now()
        );


        booking.setTotalAmount(total);


        booking.setStatus("CONFIRMED");


        booking.setUser(user);


        booking.setFlightPlan(flightPlan);


        booking.setSeatClass(seatClass);



        // reduce available seats

        flightPlan.setAvailableSeats(
                flightPlan.getAvailableSeats() - passengers
        );


        flightPlanRepository.save(flightPlan);

        Booking savedBooking =
                bookingRepository.save(booking);



        Payment payment = new Payment();

        payment.setPaymentMethod("KPay");

        payment.setAmount(savedBooking.getTotalAmount());

        payment.setPaymentStatus("PENDING");

        payment.setPaymentDate(LocalDateTime.now());

        payment.setBooking(savedBooking);

        paymentRepository.save(payment);


        savedBooking.setPayment(payment);

        return savedBooking;

	}
}
