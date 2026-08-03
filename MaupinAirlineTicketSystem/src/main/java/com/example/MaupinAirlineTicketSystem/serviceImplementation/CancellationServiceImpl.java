package com.example.MaupinAirlineTicketSystem.serviceImplementation;

import java.time.LocalDateTime;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import com.example.MaupinAirlineTicketSystem.entity.Booking;
import com.example.MaupinAirlineTicketSystem.entity.Cancellation;
import com.example.MaupinAirlineTicketSystem.entity.FlightPlan;
import com.example.MaupinAirlineTicketSystem.repository.BookingRepository;
import com.example.MaupinAirlineTicketSystem.repository.CancellationRepository;
import com.example.MaupinAirlineTicketSystem.repository.FlightPlanRepository;
import com.example.MaupinAirlineTicketSystem.service.CancellationService;


import jakarta.transaction.Transactional;

@Service
public class CancellationServiceImpl implements CancellationService{

	@Autowired
	private BookingRepository bookingRepository;


	@Autowired
	private CancellationRepository cancellationRepository;

	@Autowired
	private FlightPlanRepository flightPlanRepository;
	
	@Override
	@Transactional
	public Cancellation cancelBooking(int bookingId, String reason) {
		// TODO Auto-generated method stub
		Booking booking = bookingRepository.findById(bookingId)
	            .orElseThrow();

	    // Already cancelled
	    if ("CANCELLED".equals(booking.getStatus())) {
	        throw new RuntimeException("This booking has already been cancelled.");
	    }

	    LocalDateTime now = LocalDateTime.now();

	    LocalDateTime bookingTime = booking.getBookingDate();

	    LocalDateTime departureTime = booking.getFlightPlan().getDepartureTime();

	    // Time since booking
	    long hoursAfterBooking =
	            Duration.between(bookingTime, now).toHours();

	    if (hoursAfterBooking > 24) {
	        throw new RuntimeException(
	                "Cancellation is only allowed within 24 hours after booking."
	        );
	    }

	    // Time before departure
	    long hoursBeforeDeparture =
	            Duration.between(now, departureTime).toHours();

	    if (hoursBeforeDeparture < 24) {
	        throw new RuntimeException(
	                "Cannot cancel because the flight departs within 24 hours."
	        );
	    }

	    // Update booking status
	    booking.setStatus("CANCELLED");
	    bookingRepository.save(booking);

	    // Restore seat
	    FlightPlan flightPlan = booking.getFlightPlan();
	    if (booking.isSeatsConsumed()) {
	        flightPlan.restoreSeats(
	                booking.getSeatClass() != null ? booking.getSeatClass().getClassName() : null,
	                booking.getPassengers()
	        );
	        booking.setSeatsConsumed(false);
	        bookingRepository.save(booking);
	    }

	    // Save updated FlightPlan
	    flightPlanRepository.save(flightPlan);

	    // Create cancellation record
	    Cancellation cancellation = new Cancellation();
	    cancellation.setCancellationDate(now);
	    cancellation.setReason(reason);
	    cancellation.setRefundAmount(booking.getTotalAmount());
	    cancellation.setBooking(booking);

	    return cancellationRepository.save(cancellation);

		}

	}

