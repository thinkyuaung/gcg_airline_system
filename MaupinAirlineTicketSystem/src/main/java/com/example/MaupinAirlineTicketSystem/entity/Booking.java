package com.example.MaupinAirlineTicketSystem.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "booking")
public class Booking {

	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private int bookingId;

	    private String bookingCode;

	   // private String seatNumber;

	    private LocalDateTime bookingDate;

	    private double totalAmount;

	    private String status;

	    private String description;

	    private String country;
	    
	    private int passengers;
	    
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

	    // Foreign Key -> Promotion
	    @ManyToOne
	    @JoinColumn(name = "promotion_id")
	    private Promotion promotion;

	    @OneToOne(mappedBy = "booking")
	    private Payment payment;

		public Booking(int bookingId, String bookingCode, String seatNumber, LocalDateTime bookingDate,
				double totalAmount, String status, String country, int passengers, User user, FlightPlan flightPlan,
				SeatClass seatClass, Payment payment) {
			super();
			this.bookingId = bookingId;
			this.bookingCode = bookingCode;
			
			this.bookingDate = bookingDate;
			this.totalAmount = totalAmount;
			this.status = status;
			this.country = country;
			this.passengers = passengers;
			this.user = user;
			this.flightPlan = flightPlan;
			this.seatClass = seatClass;
			this.payment = payment;
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

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public int getPassengers() {
			return passengers;
		}

		public void setPassengers(int passengers) {
			this.passengers = passengers;
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

		public Promotion getPromotion() {
			return promotion;
		}

		public void setPromotion(Promotion promotion) {
			this.promotion = promotion;
		}

	    
		public Payment getPayment() {
			return payment;
		}

		public void setPayment(Payment payment) {
			this.payment = payment;
		}

		
}
