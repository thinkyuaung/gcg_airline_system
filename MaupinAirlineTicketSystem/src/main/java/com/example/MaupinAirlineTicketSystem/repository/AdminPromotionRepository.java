package com.example.MaupinAirlineTicketSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MaupinAirlineTicketSystem.entity.Promotion;

@Repository
public interface AdminPromotionRepository extends JpaRepository<Promotion, Integer> {

	List<Promotion> findByStatus(String status);

	
	Promotion findTopByStatusOrderByStartDateDesc(String string);

}
