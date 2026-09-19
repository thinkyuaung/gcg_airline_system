package com.example.MaupinAirlineTicketSystem.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MaupinAirlineTicketSystem.entity.User;



@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	User findByEmail(String email);

	List<User> findByStatus(String status);

	List<User> findByRoleAndStatus(String role, String status);
	User findByPassport(String passport);
	long countByStatus(String status);
	long countByRole(String role);
	User findByEmailAndStatus(String email, String status);
	List<User> findByRoleIn(List<String> roles);
	long countByRoleIn(List<String> roles);
	User findByPassportAndStatus(String passport, String status);
}
