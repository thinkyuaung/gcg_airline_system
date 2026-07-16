package com.example.Airline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Airline.entity.Booking;
import com.example.Airline.entity.User;

public interface BookingRepository extends JpaRepository<Booking, Integer>{

	 
	 List<Booking> findByStatus(String status);


	 List<Booking> findByStatusNot(String status);
}
