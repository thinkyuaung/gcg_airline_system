package com.example.MaupinAirlineTicketSystem.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.MaupinAirlineTicketSystem.entity.Airport;



@Controller
@RequestMapping("/airline")
public class AdminPromotionController {
	
	@GetMapping("/admin/promotions")
	public String promotions(Model model) {
		//List<Airline> airline =  adminAirlineService.getAllAirlines();
	//	model.addAttribute("airline", airline); 
		model.addAttribute("activeTab", "promotions");
		return "Admin/admin";
	}
	

	 @GetMapping("/admin/promotionForm")
	public String createPromotion(Model model) {
		Airport airport = new Airport();
		model.addAttribute("airport", airport); 
		return "Admin/PromotionForm";
	}

}
