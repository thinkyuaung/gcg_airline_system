package com.example.MaupinAirlineTicketSystem.service;

import com.example.MaupinAirlineTicketSystem.entity.Cancellation;

public interface CancellationService {

	Cancellation cancelBooking(
            int bookingId,
            String reason
    );
	
	
}
