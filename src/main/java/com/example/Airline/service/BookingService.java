package com.example.Airline.service;

import com.example.Airline.entity.Booking;
import com.example.Airline.entity.FlightPlan;
import com.example.Airline.entity.SeatClass;

public interface BookingService {

	 double calculateTotalPrice(
	            FlightPlan flightPlan,
	            SeatClass seatClass,
	            int passengers
	    );

	 Booking createBooking(
	            int flightPlanId,
	            int seatClassId,
	            int passengers
	    );
}
