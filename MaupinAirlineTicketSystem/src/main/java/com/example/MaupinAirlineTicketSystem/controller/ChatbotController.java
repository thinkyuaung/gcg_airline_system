package com.example.MaupinAirlineTicketSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.MaupinAirlineTicketSystem.service.ChatbotService;

@RestController
@RequestMapping("/chatbot")
public class ChatbotController {

	private final ChatbotService chatbotService;


    public ChatbotController(ChatbotService chatbotService){
        this.chatbotService = chatbotService;
    }


    @GetMapping("/categories")
    public Object categories(){

        return chatbotService.getCategories();

    }


    @GetMapping("/questions/{id}")
    public Object questions(
            @PathVariable Long id){

        return chatbotService.getQuestions(id);

    }


    @GetMapping("/answer/{id}")
    public Object answer(
            @PathVariable Long id){

        return chatbotService.getAnswer(id);

    }

}
	