package com.example.MaupinAirlineTicketSystem.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MaupinAirlineTicketSystem.entity.Booking;
import com.example.MaupinAirlineTicketSystem.entity.User;

public interface BookingRepository extends JpaRepository<Booking, Integer>{

	 
	 List<Booking> findByStatus(String status);


	 List<Booking> findByStatusNot(String status);
	 
	    List<Booking> findByUser(User user);

	 	 
	 List<Booking> findByUser_UserId(int userId);

	 List<Booking> findByUser_UserIdAndStatus(
	         int userId,
	         String status);

	 List<Booking> findByUser_UserIdAndStatusNot(
	         int userId,
	         String status);
}
