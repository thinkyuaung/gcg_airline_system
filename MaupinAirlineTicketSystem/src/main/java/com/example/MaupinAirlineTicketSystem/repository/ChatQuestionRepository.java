package com.example.MaupinAirlineTicketSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MaupinAirlineTicketSystem.entity.ChatQuestion;

public interface ChatQuestionRepository  extends JpaRepository<ChatQuestion, Long>{

	 List<ChatQuestion>
	    findByCategoryCategoryIdAndActiveTrue(Long categoryId);
}
