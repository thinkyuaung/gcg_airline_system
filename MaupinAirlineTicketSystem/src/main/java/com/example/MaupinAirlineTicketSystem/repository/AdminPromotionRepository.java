package com.example.MaupinAirlineTicketSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MaupinAirlineTicketSystem.entity.Promotion;

@Repository
public interface AdminPromotionRepository extends JpaRepository<Promotion, Integer> {

}
