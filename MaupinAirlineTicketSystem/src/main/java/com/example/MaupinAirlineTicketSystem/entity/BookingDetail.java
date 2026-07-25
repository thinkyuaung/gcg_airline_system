package com.example.MaupinAirlineTicketSystem.entity;

import java.sql.Date;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "booking_detail")
public class BookingDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bookingDetailId;

	private String passengerFirstName;
	
	private String passengerLastName;
	
	private String passport;
	
	private LocalDateTime DOB;

	@ManyToOne
	@JoinColumn(name = "booking_id")
	private Booking booking;

	public BookingDetail(int bookingDetailId, String passengerFirstName, String passengerLastName, String passport,
			LocalDateTime dOB, Booking booking) {
		super();
		this.bookingDetailId = bookingDetailId;
		this.passengerFirstName = passengerFirstName;
		this.passengerLastName = passengerLastName;
		this.passport = passport;
		DOB = dOB;
		this.booking = booking;
	}

	public BookingDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getBookingDetailId() {
		return bookingDetailId;
	}

	public void setBookingDetailId(int bookingDetailId) {
		this.bookingDetailId = bookingDetailId;
	}

	public String getPassengerFirstName() {
		return passengerFirstName;
	}

	public void setPassengerFirstName(String passengerFirstName) {
		this.passengerFirstName = passengerFirstName;
	}

	public String getPassengerLastName() {
		return passengerLastName;
	}

	public void setPassengerLastName(String passengerLastName) {
		this.passengerLastName = passengerLastName;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public LocalDateTime getDOB() {
		return DOB;
	}

	public void setDOB(LocalDateTime dOB) {
		DOB = dOB;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}


	
}
