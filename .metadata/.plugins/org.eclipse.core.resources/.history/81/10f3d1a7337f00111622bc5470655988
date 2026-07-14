package com.example.airlineTicket.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cancellation")
public class Cancellation {

	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private int cancellationId;

	    private LocalDateTime cancellationDate;

	    private String reason;

	    private double refundAmount;
	    
	 // Foreign Key -> Booking
	    @OneToOne
	    @JoinColumn(name = "booking_id")
	    private Booking booking;

	 public Cancellation(int cancellationId, LocalDateTime cancellationDate, String reason, double refundAmount,
			Booking booking) {
		super();
		this.cancellationId = cancellationId;
		this.cancellationDate = cancellationDate;
		this.reason = reason;
		this.refundAmount = refundAmount;
		this.booking = booking;
	 }

	 public Cancellation() {
		super();
		// TODO Auto-generated constructor stub
	 }

	 public int getCancellationId() {
		 return cancellationId;
	 }

	 public void setCancellationId(int cancellationId) {
		 this.cancellationId = cancellationId;
	 }

	 public LocalDateTime getCancellationDate() {
		 return cancellationDate;
	 }

	 public void setCancellationDate(LocalDateTime cancellationDate) {
		 this.cancellationDate = cancellationDate;
	 }

	 public String getReason() {
		 return reason;
	 }

	 public void setReason(String reason) {
		 this.reason = reason;
	 }

	 public double getRefundAmount() {
		 return refundAmount;
	 }

	 public void setRefundAmount(double refundAmount) {
		 this.refundAmount = refundAmount;
	 }

	 public Booking getBooking() {
		 return booking;
	 }

	 public void setBooking(Booking booking) {
		 this.booking = booking;
	 }

	 

}
