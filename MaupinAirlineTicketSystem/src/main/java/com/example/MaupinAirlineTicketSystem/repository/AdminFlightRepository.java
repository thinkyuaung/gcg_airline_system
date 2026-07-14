package com.example.MaupinAirlineTicketSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.MaupinAirlineTicketSystem.entity.Airline;



@Repository
public interface AdminFlightRepository extends JpaRepository<Airline, Integer>{

}
