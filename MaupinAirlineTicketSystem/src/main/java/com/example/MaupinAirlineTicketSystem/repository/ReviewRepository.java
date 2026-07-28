package com.example.MaupinAirlineTicketSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MaupinAirlineTicketSystem.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer>{

	 boolean existsByBooking_BookingId(int bookingId);

	  List<Review> findTop3ByOrderByReviewDateDesc();
}
