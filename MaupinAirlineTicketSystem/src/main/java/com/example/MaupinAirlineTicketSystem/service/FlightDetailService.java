package com.example.MaupinAirlineTicketSystem.service;

import java.util.List;

import com.example.MaupinAirlineTicketSystem.entity.FlightPlan;
import com.example.MaupinAirlineTicketSystem.entity.SeatClass;

public interface FlightDetailService {

	FlightPlan getFlightPlan(int id);

    List<SeatClass> getSeatClasses();

}
