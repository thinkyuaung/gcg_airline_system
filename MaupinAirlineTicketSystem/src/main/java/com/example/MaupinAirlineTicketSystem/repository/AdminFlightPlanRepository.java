package com.example.MaupinAirlineTicketSystem.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.MaupinAirlineTicketSystem.entity.FlightPlan;

@Repository
public interface AdminFlightPlanRepository extends JpaRepository<FlightPlan, Integer> {

	@Query("SELECT fp FROM FlightPlan fp WHERE fp.flight.flightId = :flightId AND fp.flight_date = :flightDate")
	List<FlightPlan> findByFlightIdAndFlightDate(@Param("flightId") Integer flightId, @Param("flightDate") Date flightDate);

}
