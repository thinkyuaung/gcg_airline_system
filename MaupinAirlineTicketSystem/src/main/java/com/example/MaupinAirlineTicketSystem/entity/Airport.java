package com.example.MaupinAirlineTicketSystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "airport")
public class Airport {
    
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer airportId;

	@NotBlank(message = "Airport name is required")
    private String airportName;

	@NotBlank(message = "Airport code is required")
    private String airportCode;

	@NotBlank(message = "City is required")
    private String city;
    
    private String image;

	

	public Airport(int airportId, String airportName, String airportCode, String city, String image) {
		super();
		this.airportId = airportId;
		this.airportName = airportName;
		this.airportCode = airportCode;
		this.city = city;
		this.image = image;
	}

	public Airport() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getAirportId() {
		return airportId;
	}

	public void setAirportId(Integer airportId) {
		this.airportId = airportId;
	}

	public String getAirportName() {
		return airportName;
	}

	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}

	public String getAirportCode() {
		return airportCode;
	}

	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
    
    
}
