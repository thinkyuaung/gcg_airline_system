package com.example.MaupinAirlineTicketSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.MaupinAirlineTicketSystem.entity.User;
import com.example.MaupinAirlineTicketSystem.repository.UserRepository;

@ControllerAdvice
public class GlobalControllerAdvice {

	@Autowired
    private UserRepository userRepository;

    @ModelAttribute("username")
    public String username() {

        Authentication auth =
                SecurityContextHolder
                .getContext()
                .getAuthentication();

        if(auth == null || !auth.isAuthenticated()){

            return "";

        }

        User user =
                userRepository.findByEmail(auth.getName());

        if(user == null){

            return "";

        }

        return user.getFirstName() + " " + user.getLastName();

    }

}
