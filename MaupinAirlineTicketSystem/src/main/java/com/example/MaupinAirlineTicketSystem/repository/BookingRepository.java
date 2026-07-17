package com.example.MaupinAirlineTicketSystem.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MaupinAirlineTicketSystem.entity.Booking;
import com.example.MaupinAirlineTicketSystem.entity.User;

public interface BookingRepository extends JpaRepository<Booking, Integer>{

	 
	 List<Booking> findByStatus(String status);


	 List<Booking> findByStatusNot(String status);
}
