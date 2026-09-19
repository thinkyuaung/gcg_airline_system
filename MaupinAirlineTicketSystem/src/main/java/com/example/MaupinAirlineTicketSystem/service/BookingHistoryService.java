package com.example.MaupinAirlineTicketSystem.service;

import java.util.List;

import com.example.MaupinAirlineTicketSystem.entity.Booking;

public interface BookingHistoryService {

	List<Booking> getAllBookings();

	List<Booking> getUpcomingBookings();

	List<Booking> getCancelledBookings();

	Booking getBookingDetail(int id);

}
