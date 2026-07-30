package com.example.MaupinAirlineTicketSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MaupinAirlineTicketSystem.entity.Booking;
import com.example.MaupinAirlineTicketSystem.entity.BookingDetail;

@Repository
public interface BookingDetailRepository extends JpaRepository<BookingDetail, Integer> {

	public List<BookingDetail> findByBooking_BookingId(int bookingId);

	 void deleteByBooking(Booking booking);
}
