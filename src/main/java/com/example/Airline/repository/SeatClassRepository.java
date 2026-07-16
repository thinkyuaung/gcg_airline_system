package com.example.Airline.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Airline.entity.SeatClass;

public interface SeatClassRepository extends JpaRepository<SeatClass, Integer>{

	SeatClass findByClassName(String className);
	
}
