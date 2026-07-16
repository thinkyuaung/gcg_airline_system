package com.example.MaupinAirlineTicketSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller 
public class HomePageController {
   
    @GetMapping("/about")
    public String showAboutPage() {
        return "about"; 
    }

    @GetMapping("/terms")
    public String showTermsPage() {
        return "terms"; 
    }
}