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
@Table(name = "payment")
public class Payment {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int paymentId;

    private String paymentMethod;

    private String paymentScreenshot;

    private double amount;

    private LocalDateTime paymentDate;

    private String paymentStatus;

 // Foreign Key -> Booking
    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

 public Payment(int paymentId, String paymentMethod, String paymentScreenshot, double amount, LocalDateTime paymentDate,
		String paymentStatus, Booking booking) {
	super();
	this.paymentId = paymentId;
	this.paymentMethod = paymentMethod;
	this.paymentScreenshot = paymentScreenshot;
	this.amount = amount;
	this.paymentDate = paymentDate;
	this.paymentStatus = paymentStatus;
	this.booking = booking;
 }

 public Payment() {
	super();
	// TODO Auto-generated constructor stub
 }

 public int getPaymentId() {
	return paymentId;
 }

 public void setPaymentId(int paymentId) {
	this.paymentId = paymentId;
 }

 public String getPaymentMethod() {
	return paymentMethod;
 }

 public void setPaymentMethod(String paymentMethod) {
	this.paymentMethod = paymentMethod;
 }

 public String getPaymentScreenshot() {
	return paymentScreenshot;
 }

 public void setPaymentScreenshot(String paymentScreenshot) {
	this.paymentScreenshot = paymentScreenshot;
 }

 public double getAmount() {
	return amount;
 }

 public void setAmount(double amount) {
	this.amount = amount;
 }

 public LocalDateTime getPaymentDate() {
	return paymentDate;
 }

 public void setPaymentDate(LocalDateTime paymentDate) {
	this.paymentDate = paymentDate;
 }

 public String getPaymentStatus() {
	return paymentStatus;
 }

 public void setPaymentStatus(String paymentStatus) {
	this.paymentStatus = paymentStatus;
 }

 public Booking getBooking() {
	return booking;
 }

 public void setBooking(Booking booking) {
	this.booking = booking;
 }
    
    
}
