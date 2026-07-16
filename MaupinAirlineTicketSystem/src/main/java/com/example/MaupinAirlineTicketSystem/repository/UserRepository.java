package com.example.MaupinAirlineTicketSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.MaupinAirlineTicketSystem.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MaupinAirlineTicketSystem.entity.User;



@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	User findByEmail(String email);

	List<User> findByStatus(String status);

	List<User> findByRoleAndStatus(String role, String status);
}
