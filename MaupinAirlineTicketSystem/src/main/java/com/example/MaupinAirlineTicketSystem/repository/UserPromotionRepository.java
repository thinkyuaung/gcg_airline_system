package com.example.MaupinAirlineTicketSystem.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MaupinAirlineTicketSystem.entity.Promotion;

public interface UserPromotionRepository extends JpaRepository<Promotion, Integer>{

	Optional<Promotion> findFirstByStatusAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            String status,
            LocalDateTime start,
            LocalDateTime end);
}
