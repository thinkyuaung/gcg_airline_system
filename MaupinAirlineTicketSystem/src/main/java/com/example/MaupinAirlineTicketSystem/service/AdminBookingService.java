package com.example.MaupinAirlineTicketSystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.MaupinAirlineTicketSystem.entity.Booking;

@Service
public interface AdminBookingService {

	public List<Booking> getAllBookings();

	public Booking getBookingById(int id);

	public Booking saveBooking(Booking booking);

}
