package com.example.MaupinAirlineTicketSystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
@RequestMapping("/airline")
public class Controller {

	@GetMapping("/")
	public String index() {
		return "index";
	}
}
