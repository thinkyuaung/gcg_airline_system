package com.example.MaupinAirlineTicketSystem.service;

import com.example.MaupinAirlineTicketSystem.entity.Booking;

public interface EmailService {

	public void sendTicketEmail(Booking booking, String serialCode);

	public void sendBookingIssuedEmail(Booking booking, String reason);

	public void sendBookingRejectedEmail(Booking booking, String reason);

	public void sendCancellationRejectedEmail(Booking booking, String reason);

}
