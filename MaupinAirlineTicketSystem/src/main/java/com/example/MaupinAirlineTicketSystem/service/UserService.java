package com.example.MaupinAirlineTicketSystem.service;

import java.util.List;

import com.example.MaupinAirlineTicketSystem.entity.User;



public interface UserService {
	
	public List<User> getAllUsers();
	User login(String email,String password);
}
