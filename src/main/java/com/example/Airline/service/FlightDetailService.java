package com.example.Airline.service;

import java.util.List;

import com.example.Airline.entity.FlightFeature;
import com.example.Airline.entity.FlightPlan;
import com.example.Airline.entity.SeatClass;

public interface FlightDetailService {

	FlightPlan getFlightPlan(int id);


    List<FlightFeature> getFeatures(int flightId);


    List<SeatClass> getSeatClasses();

}
