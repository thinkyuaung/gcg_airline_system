package com.example.MaupinAirlineTicketSystem.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.MaupinAirlineTicketSystem.entity.Booking;
import com.example.MaupinAirlineTicketSystem.entity.Cancellation;
import com.example.MaupinAirlineTicketSystem.entity.FlightPlan;
import com.example.MaupinAirlineTicketSystem.repository.BookingRepository;
import com.example.MaupinAirlineTicketSystem.repository.CancellationRepository;
import com.example.MaupinAirlineTicketSystem.repository.FlightPlanRepository;
import com.example.MaupinAirlineTicketSystem.service.AdminBookingService;
import com.example.MaupinAirlineTicketSystem.service.EmailService;

import jakarta.transaction.Transactional;

@Controller
@RequestMapping("/airline")
public class AdminBookingController {

	@Autowired
	AdminBookingService adminBookingService;

	@Autowired
	EmailService emailService;

	@Autowired
	BookingRepository bookingRepository;

	@Autowired
	CancellationRepository cancellationRepository;

	@Autowired
	FlightPlanRepository flightPlanRepository;

	@GetMapping("/admin/bookings")
	public String bookingList(Model model) {

		List<Booking> bookings = adminBookingService.getAllBookings();

		model.addAttribute("bookings", bookings);
		model.addAttribute("activeTab", "bookings");

		return "Admin/admin";
	}

	@GetMapping("/admin/booking/approve/{id}")
	public String approveBooking(@PathVariable("id") int id) {
		Booking booking = adminBookingService.getBookingById(id);
		if (booking != null) {
			String serialCode = "MAT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
			booking.setBookingCode(serialCode);
			booking.setStatus("Success");
			adminBookingService.saveBooking(booking);

			emailService.sendTicketEmail(booking, serialCode);
		}
		return "redirect:/airline/admin/bookings";
	}

	@GetMapping("/admin/booking/detail/{id}")
	public String bookingDetail(@PathVariable("id") int id, Model model) {
		Booking booking = adminBookingService.getBookingById(id);
		if (booking != null) {
			model.addAttribute("booking", booking);
		}
		return "Admin/bookingDetail";
	}

	@GetMapping("/admin/booking/issue/{id}")
	public String issueBookingForm(@PathVariable("id") int id, Model model) {
		Booking booking = adminBookingService.getBookingById(id);
		if (booking != null) {
			model.addAttribute("booking", booking);
		}
		return "Admin/issueBooking";
	}

	@PostMapping("/admin/booking/issue/{id}")
	public String issueBooking(
			@PathVariable("id") int id,
			@RequestParam("description") String description) {
		Booking booking = adminBookingService.getBookingById(id);
		if (booking != null) {
			booking.setStatus("Issued");
			booking.setCancelDescription(description);
			adminBookingService.saveBooking(booking);
		}
		return "redirect:/airline/admin/bookings";
	}

	@GetMapping("/admin/booking/approve-cancel/{id}")
	@Transactional
	public String approveCancel(@PathVariable("id") int id) {
		Booking booking = bookingRepository.findById(id).orElse(null);
		if (booking != null && "Pending Cancel".equals(booking.getStatus())) {
			booking.setStatus("CANCELLED");
			bookingRepository.save(booking);

			FlightPlan flightPlan = booking.getFlightPlan();
			flightPlan.setAvailableSeats(flightPlan.getAvailableSeats() + 1);
			flightPlanRepository.save(flightPlan);

			Cancellation cancellation = new Cancellation();
			cancellation.setCancellationDate(java.time.LocalDateTime.now());
			cancellation.setReason("Approved by admin");
			cancellation.setRefundAmount(booking.getTotalAmount());
			cancellation.setBooking(booking);
			cancellationRepository.save(cancellation);
		}
		return "redirect:/airline/admin/bookings";
	}

	@GetMapping("/admin/booking/issue-cancel/{id}")
	public String issueCancelForm(@PathVariable("id") int id, Model model) {
		Booking booking = bookingRepository.findById(id).orElse(null);
		if (booking != null) {
			model.addAttribute("booking", booking);
		}
		return "Admin/issueCancel";
	}

	@PostMapping("/admin/booking/issue-cancel/{id}")
	public String issueCancel(
			@PathVariable("id") int id,
			@RequestParam("description") String description) {
		Booking booking = bookingRepository.findById(id).orElse(null);
		if (booking != null && "Pending Cancel".equals(booking.getStatus())) {
			booking.setStatus("Success");
			booking.setCancelDescription(description);
			bookingRepository.save(booking);
		}
		return "redirect:/airline/admin/bookings";
	}
}
