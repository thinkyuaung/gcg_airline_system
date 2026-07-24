package com.example.MaupinAirlineTicketSystem.serviceImplementation;

import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.MaupinAirlineTicketSystem.entity.Booking;
import com.example.MaupinAirlineTicketSystem.service.EmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void sendTicketEmail(Booking booking, String serialCode) {
		String toEmail = booking.getUser().getEmail();
		String subject = "Your Flight Ticket - " + booking.getFlightPlan().getFlight().getFlightNumber();

		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a");

		String depart = booking.getFlightPlan().getDepartureAirport().getAirportName();
		String arrive = booking.getFlightPlan().getArrivalAirport().getAirportName();
		String flightNo = booking.getFlightPlan().getFlight().getFlightNumber();
		String airline = booking.getFlightPlan().getFlight().getAirline().getAirlineName();
		String depTime = booking.getFlightPlan().getDepartureTime().format(fmt);
		String arrTime = booking.getFlightPlan().getArrivalTime().format(fmt);
		String cls = booking.getSeatClass().getClassName();
		//String seat = booking.getSeatNumber() != null ? booking.getSeatNumber() : "N/A";
		String name = booking.getUser().getFirstName() + " " + booking.getUser().getLastName();
		double amount = booking.getTotalAmount();

		String htmlBody = "<div style='font-family:Arial,sans-serif;max-width:600px;margin:auto;border:1px solid #e0e0e0;border-radius:10px;overflow:hidden'>"
				+ "<div style='background:#3182ce;color:white;padding:20px;text-align:center'>"
				+ "<h1 style='margin:0;font-size:22px'>Maubin AirLine - eTicket</h1></div>"
				+ "<div style='padding:25px'>"
				+ "<p style='font-size:16px'>Dear <strong>" + name + "</strong>,</p>"
				+ "<p>Your flight has been confirmed. Here are your ticket details:</p>"

				// QR Code
				+ "<div style='text-align:center;margin:20px 0'>"
				+ "<img src='cid:qrCodeImage' alt='QR Code' style='width:180px;height:180px;border:2px solid #e2e8f0;border-radius:10px' />"
				+ "<p style='color:#666;font-size:12px;margin-top:5px'>Scan to view booking</p>"
				+ "</div>"

				+ "<table style='width:100%;border-collapse:collapse;margin:15px 0'>"
				+ "<tr style='background:#f8f9fa'><td style='padding:10px;font-weight:bold;border:1px solid #dee2e6'>Serial Code</td><td style='padding:10px;border:1px solid #dee2e6;color:#3182ce;font-weight:bold;font-size:18px'>" + serialCode + "</td></tr>"
				+ "<tr><td style='padding:10px;font-weight:bold;border:1px solid #dee2e6'>Airline</td><td style='padding:10px;border:1px solid #dee2e6'>" + airline + "</td></tr>"
				+ "<tr style='background:#f8f9fa'><td style='padding:10px;font-weight:bold;border:1px solid #dee2e6'>Flight Number</td><td style='padding:10px;border:1px solid #dee2e6'>" + flightNo + "</td></tr>"
				+ "<tr><td style='padding:10px;font-weight:bold;border:1px solid #dee2e6'>From</td><td style='padding:10px;border:1px solid #dee2e6'>" + depart + "</td></tr>"
				+ "<tr style='background:#f8f9fa'><td style='padding:10px;font-weight:bold;border:1px solid #dee2e6'>To</td><td style='padding:10px;border:1px solid #dee2e6'>" + arrive + "</td></tr>"
				+ "<tr><td style='padding:10px;font-weight:bold;border:1px solid #dee2e6'>Departure</td><td style='padding:10px;border:1px solid #dee2e6'>" + depTime + "</td></tr>"
				+ "<tr style='background:#f8f9fa'><td style='padding:10px;font-weight:bold;border:1px solid #dee2e6'>Arrival</td><td style='padding:10px;border:1px solid #dee2e6'>" + arrTime + "</td></tr>"
				+ "<tr><td style='padding:10px;font-weight:bold;border:1px solid #dee2e6'>Class</td><td style='padding:10px;border:1px solid #dee2e6'>" + cls + "</td></tr>"
				//+ "<tr style='background:#f8f9fa'><td style='padding:10px;font-weight:bold;border:1px solid #dee2e6'>Seat Number</td><td style='padding:10px;border:1px solid #dee2e6'>" + seat + "</td></tr>"
				+ "<tr><td style='padding:10px;font-weight:bold;border:1px solid #dee2e6'>Passengers</td><td style='padding:10px;border:1px solid #dee2e6'>" + booking.getPassengers() + "</td></tr>"
				+ "<tr style='background:#f8f9fa'><td style='padding:10px;font-weight:bold;border:1px solid #dee2e6'>Total Amount</td><td style='padding:10px;border:1px solid #dee2e6;font-weight:bold'>$" + String.format("%.2f", amount) + "</td></tr>"
				+ "</table>"
				+ "<p style='color:#666;font-size:13px'>Use your <strong>Serial Code</strong> and <strong>Last Name</strong> to manage your booking on our website."
				+ "<br><br>"
				+ "<a href='http://localhost:8080/airline/manage-booking' "
				+ "style='color:#0d6efd;text-decoration:underline;'>"
				+ "http://localhost:8080/airline/manage-booking"
				+ "</a>"
				+ "</p>"

				+ "</div>"
				+ "<div style='background:#f8f9fa;padding:15px;text-align:center;font-size:12px;color:#999'>© 2026 Maubin AirLine. All rights reserved.</div>"
				+ "</div>";

		try {
			Path qrPath = Paths.get("uploads", "qrCodeBooking.png");
			FileSystemResource qrImage = new FileSystemResource(qrPath.toFile());

			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom("thinkyuaung.tka@gmail.com", "Maubin AirLine");
			helper.setTo(toEmail);
			helper.setSubject(subject);
			helper.setText(htmlBody, true);
			helper.addInline("qrCodeImage", qrImage);
			mailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
