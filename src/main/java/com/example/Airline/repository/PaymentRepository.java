package com.example.Airline.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Airline.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{

}
