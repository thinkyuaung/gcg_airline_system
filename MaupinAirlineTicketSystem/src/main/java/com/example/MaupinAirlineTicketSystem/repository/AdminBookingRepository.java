package com.example.MaupinAirlineTicketSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MaupinAirlineTicketSystem.entity.Booking;

@Repository
public interface AdminBookingRepository extends JpaRepository<Booking, Integer> {

}
