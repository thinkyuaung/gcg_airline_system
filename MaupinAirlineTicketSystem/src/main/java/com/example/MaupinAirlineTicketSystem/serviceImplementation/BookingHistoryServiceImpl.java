package com.example.MaupinAirlineTicketSystem.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MaupinAirlineTicketSystem.entity.Booking;
import com.example.MaupinAirlineTicketSystem.entity.User;
import com.example.MaupinAirlineTicketSystem.repository.BookingRepository;
import com.example.MaupinAirlineTicketSystem.repository.UserRepository;
import com.example.MaupinAirlineTicketSystem.service.BookingHistoryService;

@Service
public class BookingHistoryServiceImpl implements BookingHistoryService
{

	@Autowired
	private BookingRepository bookingRepository;


	@Autowired
	private UserRepository userRepository;

	
	@Override
	public List<Booking> getAllBookings(){

	    return bookingRepository.findAll();

	}



	@Override
	public List<Booking> getUpcomingBookings(){

	    return bookingRepository
	            .findByStatusNot("CANCELLED");

	}




	@Override
	public List<Booking> getCancelledBookings(){

	    return bookingRepository
	            .findByStatus("CANCELLED");

	}



	@Override
	public Booking getBookingDetail(int id){

	    return bookingRepository
	            .findById(id)
	            .orElseThrow();

	}

}
