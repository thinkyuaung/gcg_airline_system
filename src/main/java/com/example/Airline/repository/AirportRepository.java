package com.example.Airline.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Airline.entity.Airport;

public interface AirportRepository extends JpaRepository<Airport, Integer>{

}
