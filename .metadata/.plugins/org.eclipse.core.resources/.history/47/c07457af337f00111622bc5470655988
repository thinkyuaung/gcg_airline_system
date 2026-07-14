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
@Table(name = "flight_plan")

public class FlightPlan {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int flightPlanId;

    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;
    
    private int availableSeats;

    private double price;
    
    // Foreign Key -> Flight
    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;


    // Foreign Key -> Airport (Departure)
    @ManyToOne
    @JoinColumn(name = "departure_airport_id")
    private Airport departureAirport;
    
 // Foreign Key -> Airport (Arrival)
    @ManyToOne
    @JoinColumn(name = "arrival_airport_id")
    private Airport arrivalAirport;

 public FlightPlan(int flightPlanId, LocalDateTime departureTime, LocalDateTime arrivalTime, int availableSeats,
		double price, Flight flight, Airport departureAirport, Airport arrivalAirport) {
	super();
	this.flightPlanId = flightPlanId;
	this.departureTime = departureTime;
	this.arrivalTime = arrivalTime;
	this.availableSeats = availableSeats;
	this.price = price;
	this.flight = flight;
	this.departureAirport = departureAirport;
	this.arrivalAirport = arrivalAirport;
 }

 public FlightPlan() {
	super();
	// TODO Auto-generated constructor stub
 }

 public int getFlightPlanId() {
	return flightPlanId;
 }

 public void setFlightPlanId(int flightPlanId) {
	this.flightPlanId = flightPlanId;
 }

 public LocalDateTime getDepartureTime() {
	return departureTime;
 }

 public void setDepartureTime(LocalDateTime departureTime) {
	this.departureTime = departureTime;
 }

 public LocalDateTime getArrivalTime() {
	return arrivalTime;
 }

 public void setArrivalTime(LocalDateTime arrivalTime) {
	this.arrivalTime = arrivalTime;
 }

 public int getAvailableSeats() {
	return availableSeats;
 }

 public void setAvailableSeats(int availableSeats) {
	this.availableSeats = availableSeats;
 }

 public double getPrice() {
	return price;
 }

 public void setPrice(double price) {
	this.price = price;
 }

 public Flight getFlight() {
	return flight;
 }

 public void setFlight(Flight flight) {
	this.flight = flight;
 }

 public Airport getDepartureAirport() {
	return departureAirport;
 }

 public void setDepartureAirport(Airport departureAirport) {
	this.departureAirport = departureAirport;
 }

 public Airport getArrivalAirport() {
	return arrivalAirport;
 }

 public void setArrivalAirport(Airport arrivalAirport) {
	this.arrivalAirport = arrivalAirport;
 }

 
}
