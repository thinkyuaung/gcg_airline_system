package com.example.airlineTicket.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "airline")
public class Airline {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer airlineId;
	 private String airlineName;
	 private String airlineCode;
	 private String country;
	 private String logo;
	 public Airline(Integer airlineId, String airlineName, String airlineCode, String country, String logo) {
		super();
		this.airlineId = airlineId;
		this.airlineName = airlineName;
		this.airlineCode = airlineCode;
		this.country = country;
		this.logo = logo;
	 }
	 public Airline() {
		super();
		// TODO Auto-generated constructor stub
	 }
	 public Integer getAirlineId() {
		 return airlineId;
	 }
	 public void setAirlineId(Integer airlineId) {
		 this.airlineId = airlineId;
	 }
	 public String getAirlineName() {
		 return airlineName;
	 }
	 public void setAirlineName(String airlineName) {
		 this.airlineName = airlineName;
	 }
	 public String getAirlineCode() {
		 return airlineCode;
	 }
	 public void setAirlineCode(String airlineCode) {
		 this.airlineCode = airlineCode;
	 }
	 public String getCountry() {
		 return country;
	 }
	 public void setCountry(String country) {
		 this.country = country;
	 }
	 public String getLogo() {
		 return logo;
	 }
	 public void setLogo(String logo) {
		 this.logo = logo;
	 }
	 
	 
}
