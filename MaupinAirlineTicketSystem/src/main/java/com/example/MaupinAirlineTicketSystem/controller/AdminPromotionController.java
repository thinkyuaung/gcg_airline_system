package com.example.MaupinAirlineTicketSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.MaupinAirlineTicketSystem.entity.Promotion;
import com.example.MaupinAirlineTicketSystem.repository.AdminPromotionRepository;
import com.example.MaupinAirlineTicketSystem.service.AdminPromotionService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/airline")
public class AdminPromotionController {

	@Autowired
	AdminPromotionService adminPromotionService;

	@Autowired
	AdminPromotionRepository adminPromotionRepository;

	private boolean hasDuplicatePromotion(Promotion promotion) {
		if (promotion.getStartDate() == null || promotion.getEndDate() == null || promotion.getDescription() == null) {
			return false;
		}
		return adminPromotionRepository
				.findDuplicateByDatesAndDescription(promotion.getStartDate().toLocalDate(),
						promotion.getEndDate().toLocalDate(), promotion.getDescription().trim())
				.stream().anyMatch(p -> p.getPromotionId() != promotion.getPromotionId());
	}

	@GetMapping("/admin/promotions")
	public String promotions(Model model) {
		List<Promotion> promotions = adminPromotionService.getAllPromotions();

		model.addAttribute("promotions", promotions);
		model.addAttribute("activeTab", "promotions");

		return "Admin/admin";
	}

	@GetMapping("/admin/promotionForm")
	public String createPromotion(Model model) {
		Promotion promotion = new Promotion();
		model.addAttribute("promotion", promotion);
		return "Admin/PromotionForm";
	}

	@PostMapping("/admin/promotion")
	public String savePromotion(@Valid @ModelAttribute("promotion") Promotion promotion, BindingResult result) {
		if (result.hasErrors()) {
			return "Admin/PromotionForm";
		}
		if (hasDuplicatePromotion(promotion)) {
			result.rejectValue("startDate", "error.startDate",
					"A promotion with these dates and description already exists.");
			return "Admin/PromotionForm";
		}
		adminPromotionService.savePromotion(promotion);
		return "redirect:/airline/admin/promotions";
	}

	@GetMapping("/admin/promotion/edit/{id}")
	public String editPromotion(@PathVariable("id") int id, Model model) {
		Promotion promotion = adminPromotionService.getPromotionById(id);
		model.addAttribute("promotion", promotion);
		return "Admin/PromotionForm";
	}

	@GetMapping("/admin/promotion/detail/{id}")
	public String promotionDetail(@PathVariable("id") int id, Model model) {
		Promotion promotion = adminPromotionService.getPromotionById(id);
		if (promotion != null) {
			model.addAttribute("promotion", promotion);
		}
		return "Admin/promotionDetail";
	}

	@PostMapping("/admin/promotion/update")
	public String updatePromotion(@Valid @ModelAttribute("promotion") Promotion promotion, BindingResult result) {
		if (result.hasErrors()) {
			return "Admin/PromotionForm";
		}
		if (hasDuplicatePromotion(promotion)) {
			result.rejectValue("startDate", "error.startDate",
					"A promotion with these dates and description already exists.");
			return "Admin/PromotionForm";
		}
		adminPromotionService.savePromotion(promotion);
		return "redirect:/airline/admin/promotions";
	}

	@GetMapping("/admin/promotion/delete/{id}")
	public String deletePromotion(@PathVariable("id") int id) {
		adminPromotionService.deletePromotionById(id);
		return "redirect:/airline/admin/promotions";
	}

}
