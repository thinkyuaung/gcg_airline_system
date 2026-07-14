package com.example.MaupinAirlineTicketSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MaupinAirlineTicketSystem.entity.FlightPlan;

@Repository
public interface AdminFlightPlanRepository extends JpaRepository<FlightPlan, Integer> {

}
