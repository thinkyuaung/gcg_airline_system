package com.example.MaupinAirlineTicketSystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.MaupinAirlineTicketSystem.entity.Promotion;

@Service
public interface AdminPromotionService {

	public Promotion savePromotion(Promotion promotion);

	public List<Promotion> getAllPromotions();

	public Promotion getPromotionById(int id);

	public void deletePromotionById(int id);

}
