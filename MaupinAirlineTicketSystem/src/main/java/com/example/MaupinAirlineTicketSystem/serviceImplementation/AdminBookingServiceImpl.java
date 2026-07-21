package com.example.MaupinAirlineTicketSystem.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MaupinAirlineTicketSystem.entity.Booking;
import com.example.MaupinAirlineTicketSystem.repository.AdminBookingRepository;
import com.example.MaupinAirlineTicketSystem.service.AdminBookingService;

@Service
public class AdminBookingServiceImpl implements AdminBookingService {

	@Autowired
	AdminBookingRepository adminBookingRepo;

	@Override
	public List<Booking> getAllBookings() {
		return adminBookingRepo.findAll();
	}

	@Override
	public Booking getBookingById(int id) {
		return adminBookingRepo.findById(id).orElse(null);
	}

	@Override
	public Booking saveBooking(Booking booking) {
		return adminBookingRepo.save(booking);
	}

}
