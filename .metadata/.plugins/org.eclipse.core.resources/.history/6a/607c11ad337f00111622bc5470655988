package com.example.airlineTicket.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "flight_feature")
public class FlightFeature {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int featureId;

    private String featureName;

    private String icon;
    
 // Foreign Key -> Flight
    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

 public FlightFeature(int featureId, String featureName, String icon, Flight flight) {
	super();
	this.featureId = featureId;
	this.featureName = featureName;
	this.icon = icon;
	this.flight = flight;
 }

 public FlightFeature() {
	super();
	// TODO Auto-generated constructor stub
 }

 public int getFeatureId() {
	return featureId;
 }

 public void setFeatureId(int featureId) {
	this.featureId = featureId;
 }

 public String getFeatureName() {
	return featureName;
 }

 public void setFeatureName(String featureName) {
	this.featureName = featureName;
 }

 public String getIcon() {
	return icon;
 }

 public void setIcon(String icon) {
	this.icon = icon;
 }

 public Flight getFlight() {
	return flight;
 }

 public void setFlight(Flight flight) {
	this.flight = flight;
 }

    

}
