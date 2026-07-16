package com.example.Airline.serviceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.Airline.entity.Payment;
import com.example.Airline.repository.PaymentRepository;
import com.example.Airline.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService{

	@Autowired
	private PaymentRepository paymentRepository;



	private final String uploadFolder =
	        "uploads/payment/";
	
	@Override
	public Payment uploadScreenshot(int paymentId, MultipartFile file) {
		// TODO Auto-generated method stub
		Payment payment =
	            paymentRepository.findById(paymentId)
	            .orElseThrow();



	    try {


	        Path path =
	            Paths.get(uploadFolder);


	        if(!Files.exists(path)){
	            Files.createDirectories(path);
	        }



	        String fileName =
	                System.currentTimeMillis() + "_"
	                + file.getOriginalFilename();



	        Path filePath =
	            path.resolve(fileName);



	        Files.copy(
	        	    file.getInputStream(),
	        	    filePath,
	        	    StandardCopyOption.REPLACE_EXISTING
	        	);


	        payment.setPaymentScreenshot(
	                fileName
	        );


	        payment.setPaymentStatus(
	                "PENDING"
	        );



	        return paymentRepository.save(payment);



	    }catch(IOException e){

	        throw new RuntimeException(e);

	    }


	}

}
