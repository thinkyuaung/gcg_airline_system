package com.example.MaupinAirlineTicketSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.MaupinAirlineTicketSystem.entity.SeatClass;

public interface SeatClassRepository extends JpaRepository<SeatClass, Integer>{

	SeatClass findByClassName(String className);
	
}
