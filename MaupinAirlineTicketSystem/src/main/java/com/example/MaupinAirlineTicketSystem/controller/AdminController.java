package com.example.MaupinAirlineTicketSystem.controller;

import java.io.File;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.MaupinAirlineTicketSystem.entity.Airline;
import com.example.MaupinAirlineTicketSystem.service.AdminFlightService;



@Controller
@RequestMapping("/airline")
public class AdminController {
	
	@Autowired
	AdminFlightService adminFlightService;
	
	@GetMapping("/admin")
	public String adminPage() {
		return "Admin/admin";
	}

}
