package com.example.Airline.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Airline.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
