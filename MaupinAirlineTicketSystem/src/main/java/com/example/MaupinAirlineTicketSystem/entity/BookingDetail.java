package com.example.MaupinAirlineTicketSystem.entity;

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

	private String passengerName;

	private String passport;

	@ManyToOne
	@JoinColumn(name = "booking_id")
	private Booking booking;

	public BookingDetail() {
		super();
	}

	public BookingDetail(int bookingDetailId, String passengerName, String passport, Booking booking) {
		super();
		this.bookingDetailId = bookingDetailId;
		this.passengerName = passengerName;
		this.passport = passport;
		this.booking = booking;
	}

	public int getBookingDetailId() {
		return bookingDetailId;
	}

	public void setBookingDetailId(int bookingDetailId) {
		this.bookingDetailId = bookingDetailId;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

}
