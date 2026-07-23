package com.example.MaupinAirlineTicketSystem.serviceImplementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.MaupinAirlineTicketSystem.entity.ChatCategory;
import com.example.MaupinAirlineTicketSystem.entity.ChatQuestion;
import com.example.MaupinAirlineTicketSystem.repository.ChatCategoryRepository;
import com.example.MaupinAirlineTicketSystem.repository.ChatQuestionRepository;
import com.example.MaupinAirlineTicketSystem.service.ChatbotService;

@Service
public class ChatbotServiceImpl implements ChatbotService{

	private final ChatCategoryRepository categoryRepository;

    private final ChatQuestionRepository questionRepository;
    
    public ChatbotServiceImpl(
            ChatCategoryRepository categoryRepository,
            ChatQuestionRepository questionRepository){

        this.categoryRepository = categoryRepository;
        this.questionRepository = questionRepository;

    }

    
	@Override
	public List<ChatCategory> getCategories() {
		// TODO Auto-generated method stub
		return categoryRepository
                .findByActiveTrueOrderByDisplayOrderAsc();
	}

	@Override
	public List<ChatQuestion> getQuestions(Long categoryId) {
		// TODO Auto-generated method stub
		return questionRepository
                .findByCategoryCategoryIdAndActiveTrue(categoryId);
	}

	@Override
	public ChatQuestion getAnswer(Long questionId) {
		// TODO Auto-generated method stub
		return questionRepository
                .findById(questionId)
                .orElse(null);
	}

}
