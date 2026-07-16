package com.example.MaupinAirlineTicketSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.MaupinAirlineTicketSystem.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
