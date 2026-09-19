package com.example.MaupinAirlineTicketSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.MaupinAirlineTicketSystem.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{

}
