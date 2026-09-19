package com.example.MaupinAirlineTicketSystem.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.MaupinAirlineTicketSystem.entity.Promotion;

@Repository
public interface AdminPromotionRepository extends JpaRepository<Promotion, Integer> {

	List<Promotion> findByStatus(String status);
	Promotion findTopByStatusOrderByStartDateDesc(String string);

	@Query("SELECT p FROM Promotion p WHERE FUNCTION('date', p.startDate) = :startDate "
			+ "AND FUNCTION('date', p.endDate) = :endDate AND p.description = :description")
	List<Promotion> findDuplicateByDatesAndDescription(@Param("startDate") LocalDate startDate,
			@Param("endDate") LocalDate endDate, @Param("description") String description);

	@Query("SELECT p FROM Promotion p WHERE p.status = :status AND p.startDate <= :now AND p.endDate >= :now ORDER BY p.startDate DESC")
	List<Promotion> findCurrentActivePromotions(@Param("status") String status, @Param("now") LocalDateTime now);

	@Query("SELECT p FROM Promotion p WHERE p.status = :status AND p.startDate >= :startOfMonth AND p.startDate < :endOfMonth ORDER BY p.startDate DESC")
	List<Promotion> findPromotionsInMonth(@Param("status") String status, @Param("startOfMonth") LocalDateTime startOfMonth, @Param("endOfMonth") LocalDateTime endOfMonth);
}
