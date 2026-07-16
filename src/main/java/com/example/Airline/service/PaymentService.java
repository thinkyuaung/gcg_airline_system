package com.example.Airline.service;

import org.springframework.web.multipart.MultipartFile;

import com.example.Airline.entity.Payment;

public interface PaymentService {

	 Payment uploadScreenshot(
	            int paymentId,
	            MultipartFile file
	    );
}
