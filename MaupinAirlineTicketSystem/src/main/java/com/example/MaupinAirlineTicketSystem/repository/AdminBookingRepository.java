package com.example.MaupinAirlineTicketSystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.MaupinAirlineTicketSystem.entity.Booking;

@Repository
public interface AdminBookingRepository extends JpaRepository<Booking, Integer> {

	public Optional<Booking> findByBookingCodeAndUserLastName(String bookingCode, String lastName);

	@Query("SELECT COALESCE(SUM(b.totalAmount), 0) FROM Booking b WHERE b.status IN ('Success', 'Issued')")
	double sumEarnings();

}
