package com.example.Airline.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Airline.entity.Booking;
import com.example.Airline.entity.User;
import com.example.Airline.repository.BookingRepository;
import com.example.Airline.repository.UserRepository;
import com.example.Airline.service.BookingHistoryService;

@Service
public class BookingHistoryServiceImpl implements BookingHistoryService
{

	@Autowired
	private BookingRepository bookingRepository;


	@Autowired
	private UserRepository userRepository;

	
	@Override
	public List<Booking> getBookingHistory(int userId) {
		// TODO Auto-generated method stub
		 User user =
			        userRepository.findById(userId)
			        .orElseThrow();


			    return bookingRepository.findByUser(user);
	}

}
