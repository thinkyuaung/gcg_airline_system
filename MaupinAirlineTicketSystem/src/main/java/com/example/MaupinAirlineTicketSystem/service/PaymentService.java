package com.example.MaupinAirlineTicketSystem.service;

import org.springframework.web.multipart.MultipartFile;

import com.example.MaupinAirlineTicketSystem.entity.Payment;

public interface PaymentService {

	Payment getPayment(int paymentId);
	
	 Payment uploadScreenshot(
	            int paymentId,
	            MultipartFile file
	    );
}
