package com.example.MaupinAirlineTicketSystem.service;

import com.example.MaupinAirlineTicketSystem.entity.Booking;
import com.example.MaupinAirlineTicketSystem.entity.FlightPlan;
import com.example.MaupinAirlineTicketSystem.entity.SeatClass;

public interface BookingService {

	 double calculateTotalPrice(
	            FlightPlan flightPlan,
	            SeatClass seatClass,
	            int passengers
	    );

	 Booking createBooking(
		        int flightPlanId,
		        String seatClass,
		        int passengers
		);
}
