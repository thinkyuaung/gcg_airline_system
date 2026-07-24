package com.example.MaupinAirlineTicketSystem.service;

import com.example.MaupinAirlineTicketSystem.entity.Booking;

public interface EmailService {

	public void sendTicketEmail(Booking booking, String serialCode);

}
