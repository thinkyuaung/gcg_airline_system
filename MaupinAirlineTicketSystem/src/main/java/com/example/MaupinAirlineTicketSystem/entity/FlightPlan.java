package com.example.MaupinAirlineTicketSystem.entity;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import com.example.MaupinAirlineTicketSystem.validation.TodayOrFuture;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "flight_plan")

public class FlightPlan {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int flightPlanId;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@NotNull(message = "Departure time is required")
	@TodayOrFuture(message = "Departure time must be today or in the future")
	private LocalDateTime departureTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@NotNull(message = "Arrival time is required")
	@TodayOrFuture(message = "Arrival time must be today or in the future")
	private LocalDateTime arrivalTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Flight date is required")
	@TodayOrFuture(message = "Flight date must be today or in the future")
	private Date flight_date;

	private int availableSeats;
	
	private int ecomonySeats;
	
	private int businessSeats;
	
	private int firseClassSeats;

	@DecimalMin(value = "5.0", message = "Price must be greater than 5 dollars")
	private double price;

	// Foreign Key -> Flight
	@ManyToOne
	@JoinColumn(name = "flight_id")
	@NotNull(message = "Please select a flight")
	private Flight flight;

	// Foreign Key -> Airport (Departure)
	@ManyToOne
	@JoinColumn(name = "departure_airport_id")
	@NotNull(message = "Please select a departure airport")
	private Airport departureAirport;

	// Foreign Key -> Airport (Arrival)
	@ManyToOne
	@JoinColumn(name = "arrival_airport_id")
	@NotNull(message = "Please select an arrival airport")
	private Airport arrivalAirport;

	

	public FlightPlan(int flightPlanId, LocalDateTime departureTime, LocalDateTime arrivalTime, Date flight_date,
			int availableSeats, int ecomonySeats, int businessSeats, int firseClassSeats, double price, Flight flight,
			Airport departureAirport, Airport arrivalAirport) {
		super();
		this.flightPlanId = flightPlanId;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.flight_date = flight_date;
		this.availableSeats = availableSeats;
		this.ecomonySeats = ecomonySeats;
		this.businessSeats = businessSeats;
		this.firseClassSeats = firseClassSeats;
		this.price = price;
		this.flight = flight;
		this.departureAirport = departureAirport;
		this.arrivalAirport = arrivalAirport;
	}

	public FlightPlan() {
		super();
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

	public Date getFlight_date() {
		return flight_date;
	}

	public void setFlight_date(Date flight_date) {
		this.flight_date = flight_date;
	}

	public int getEcomonySeats() {
		return ecomonySeats;
	}

	public void setEcomonySeats(int ecomonySeats) {
		this.ecomonySeats = ecomonySeats;
	}

	public int getBusinessSeats() {
		return businessSeats;
	}

	public void setBusinessSeats(int businessSeats) {
		this.businessSeats = businessSeats;
	}

	public int getFirseClassSeats() {
		return firseClassSeats;
	}

	public void setFirseClassSeats(int firseClassSeats) {
		this.firseClassSeats = firseClassSeats;
	}

}
