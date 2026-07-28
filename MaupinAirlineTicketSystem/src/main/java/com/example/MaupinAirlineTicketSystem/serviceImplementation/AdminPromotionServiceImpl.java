package com.example.MaupinAirlineTicketSystem.serviceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MaupinAirlineTicketSystem.entity.Promotion;
import com.example.MaupinAirlineTicketSystem.repository.AdminPromotionRepository;
import com.example.MaupinAirlineTicketSystem.service.AdminPromotionService;

@Service
public class AdminPromotionServiceImpl implements AdminPromotionService {

	@Autowired
	AdminPromotionRepository adminPromotionRepo;

	@Override
	public Promotion savePromotion(Promotion promotion) {
		return adminPromotionRepo.save(promotion);
	}

	@Override
	public List<Promotion> getAllPromotions() {
		return adminPromotionRepo.findAll();
	}

	@Override
	public Promotion getPromotionById(int id) {
	
		return adminPromotionRepo.findById(id).get();
	}

	@Override
	public void deletePromotionById(int id) {
		adminPromotionRepo.deleteById(id);
	}

}
