package com.example.MaupinAirlineTicketSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.MaupinAirlineTicketSystem.entity.Payment;
import com.example.MaupinAirlineTicketSystem.service.PaymentService;

@Controller
@RequestMapping("/airline")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;



	@GetMapping("/payment/{id}")
	public String paymentPage(
	        @PathVariable int id,
	        Model model){


		Payment payment =
	            paymentService.getPayment(id);


	    model.addAttribute(
	            "payment",
	            payment
	    );

	    return "userview/payment";


	}



	@PostMapping("/payment/upload")
	public String uploadPayment(
	        @RequestParam int paymentId,
	        @RequestParam MultipartFile screenshot,
	        Model model){

		Payment payment =
		        paymentService.uploadScreenshot(paymentId, screenshot);

		
		model.addAttribute(
		        "payment",
		        payment
		    );


	    return "userview/paymentSuccess";

	}
}
