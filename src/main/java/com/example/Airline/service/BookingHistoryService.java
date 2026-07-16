package com.example.Airline.service;

import java.util.List;

import com.example.Airline.entity.Booking;

public interface BookingHistoryService {

	List<Booking> getAllBookings();


    List<Booking> getUpcomingBookings();


    List<Booking> getCancelledBookings();


    Booking getBookingDetail(int id);

	
}
