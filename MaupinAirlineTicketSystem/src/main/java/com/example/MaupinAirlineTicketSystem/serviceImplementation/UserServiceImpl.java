package com.example.MaupinAirlineTicketSystem.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MaupinAirlineTicketSystem.entity.User;
import com.example.MaupinAirlineTicketSystem.repository.UserRepository;
import com.example.MaupinAirlineTicketSystem.service.UserService;



@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> users = userRepository.findAll();
		return users;
	}

	@Override
	public User login(String email, String password) {
		// TODO Auto-generated method stub
		User user = userRepository.findByEmail(email);
		if (user != null && user.getPassword().equals(password) && user.getStatus().equalsIgnoreCase("active")) {

			return user;
		}
		return null;
	}

}
