package com.example.Airline.service;

import com.example.Airline.entity.Cancellation;

public interface CancellationService {

	Cancellation cancelBooking(
            int bookingId,
            String reason
    );
	
	
}
