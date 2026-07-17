package com.example.Airline.service;

import java.util.List;

import com.example.Airline.entity.FlightPlan;
import com.example.Airline.entity.SeatClass;

public interface FlightDetailService {

	FlightPlan getFlightPlan(int id);

    List<SeatClass> getSeatClasses();

}
