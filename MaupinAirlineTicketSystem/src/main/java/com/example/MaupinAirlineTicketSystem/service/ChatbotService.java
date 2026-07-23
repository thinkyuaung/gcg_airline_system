package com.example.MaupinAirlineTicketSystem.service;

import java.util.List;

import com.example.MaupinAirlineTicketSystem.entity.ChatCategory;
import com.example.MaupinAirlineTicketSystem.entity.ChatQuestion;

public interface ChatbotService {

	List<ChatCategory> getCategories();


    List<ChatQuestion> getQuestions(Long categoryId);


    ChatQuestion getAnswer(Long questionId);
}
