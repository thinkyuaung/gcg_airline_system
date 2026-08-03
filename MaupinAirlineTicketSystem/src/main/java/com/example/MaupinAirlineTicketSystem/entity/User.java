package com.example.MaupinAirlineTicketSystem.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Past;
@Entity
@Table(name = "users")

public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	@NotBlank(message = "First name is required.")
	@Size(max = 30, message = "First name must not exceed 30 characters.")
	@Pattern(
	    regexp = "^[A-Za-z ]+$",
	    message = "First name must contain only letters."
	)
	private String firstName;
	
	@NotBlank(message = "Last name is required.")
	@Size(max = 30, message = "Last name must not exceed 30 characters.")
	@Pattern(
	    regexp = "^[A-Za-z ]+$",
	    message = "Last name must contain only letters."
	)
	private String lastName;
	
	@NotBlank(message = "Passport number is required.")
	@Size(max = 12, message = "Passport number must not exceed 12 characters.")
	@Pattern(
	    regexp = "^[A-Za-z0-9]+$",
	    message = "Passport number must contain only letters and numbers."
	)
	private String passport;
	
	@Past(message = "Date of Birth cannot be in the future.")
	private LocalDate dob;
	
	@NotBlank(message = "Email is required.")
	@Email(
	    regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
	    message = "Invalid email format."
	)
	private String email;
	
	private String password;
	@Column
	@NotBlank(message = "Phone number is required.")
	@Pattern(
	    regexp = "^[0-9]+$",
	    message = "Phone number must contain only digits."
	)
	private String phoneNumber;
	private String role = "USER";
	private String status = "active";

	public User(int userId, String firstName, String lastName, String passport, LocalDate dob, String email,
			String password, String phoneNumber, String role, String status) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.passport = passport;
		this.dob = dob;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.role = role;
		this.status = status;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
