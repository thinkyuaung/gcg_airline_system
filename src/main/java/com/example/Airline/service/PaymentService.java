package com.example.Airline.service;

import org.springframework.web.multipart.MultipartFile;

import com.example.Airline.entity.Payment;

public interface PaymentService {

	Payment getPayment(int paymentId);
	
	 Payment uploadScreenshot(
	            int paymentId,
	            MultipartFile file
	    );
}
