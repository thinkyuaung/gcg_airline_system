package com.example.MaupinAirlineTicketSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MaupinAirlineTicketSystem.entity.SeatClass;

@Repository
public interface AdminSeatRepository extends JpaRepository<SeatClass, Integer>{

}
