package com.example.airlineTicket.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="flight")
public class Flight {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	
	private int flightId;

    private String flightNumber;
    private int totalSeats;
    private String status;
    
    @ManyToOne
    @JoinColumn(name="airline_id")
    private Airline airline;

	public Flight(int flightId, String flightNumber, int totalSeats, String status,
			Airline airline) {
		super();
		this.flightId = flightId;
		this.flightNumber = flightNumber;
		this.totalSeats = totalSeats;
		this.status = status;
		this.airline = airline;
	}

	public Flight() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}
    
    
}
