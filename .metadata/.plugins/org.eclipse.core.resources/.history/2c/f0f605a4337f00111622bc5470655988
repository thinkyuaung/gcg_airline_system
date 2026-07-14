package com.example.airlineTicket.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "booking")
public class Booking {

	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private int bookingId;

	    private String bookingCode;

	    private String seatNumber;

	    private LocalDateTime bookingDate;

	    private double totalAmount;

	    private String status;

	    private String country;
	    
	    // Foreign Key -> User
	    @ManyToOne
	    @JoinColumn(name = "user_id")
	    private User user;


	    // Foreign Key -> FlightPlan
	    @ManyToOne
	    @JoinColumn(name = "flight_plan_id")
	    private FlightPlan flightPlan;


	    // Foreign Key -> SeatClass
	    @ManyToOne
	    @JoinColumn(name = "class_id")
	    private SeatClass seatClass;


		public Booking(int bookingId, String bookingCode, String seatNumber, LocalDateTime bookingDate,
				double totalAmount, String status, String country, User user, FlightPlan flightPlan,
				SeatClass seatClass) {
			super();
			this.bookingId = bookingId;
			this.bookingCode = bookingCode;
			this.seatNumber = seatNumber;
			this.bookingDate = bookingDate;
			this.totalAmount = totalAmount;
			this.status = status;
			this.country = country;
			this.user = user;
			this.flightPlan = flightPlan;
			this.seatClass = seatClass;
		}


		public Booking() {
			super();
			// TODO Auto-generated constructor stub
		}


		public int getBookingId() {
			return bookingId;
		}


		public void setBookingId(int bookingId) {
			this.bookingId = bookingId;
		}


		public String getBookingCode() {
			return bookingCode;
		}


		public void setBookingCode(String bookingCode) {
			this.bookingCode = bookingCode;
		}


		public String getSeatNumber() {
			return seatNumber;
		}


		public void setSeatNumber(String seatNumber) {
			this.seatNumber = seatNumber;
		}


		public LocalDateTime getBookingDate() {
			return bookingDate;
		}


		public void setBookingDate(LocalDateTime bookingDate) {
			this.bookingDate = bookingDate;
		}


		public double getTotalAmount() {
			return totalAmount;
		}


		public void setTotalAmount(double totalAmount) {
			this.totalAmount = totalAmount;
		}


		public String getStatus() {
			return status;
		}


		public void setStatus(String status) {
			this.status = status;
		}


		public String getCountry() {
			return country;
		}


		public void setCountry(String country) {
			this.country = country;
		}


		public User getUser() {
			return user;
		}


		public void setUser(User user) {
			this.user = user;
		}


		public FlightPlan getFlightPlan() {
			return flightPlan;
		}


		public void setFlightPlan(FlightPlan flightPlan) {
			this.flightPlan = flightPlan;
		}


		public SeatClass getSeatClass() {
			return seatClass;
		}


		public void setSeatClass(SeatClass seatClass) {
			this.seatClass = seatClass;
		}

	    
}
