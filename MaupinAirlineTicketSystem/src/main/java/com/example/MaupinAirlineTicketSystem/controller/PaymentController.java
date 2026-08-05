package com.example.MaupinAirlineTicketSystem.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.MaupinAirlineTicketSystem.repository.BookingRepository;
import com.example.MaupinAirlineTicketSystem.repository.FlightPlanRepository;
import com.example.MaupinAirlineTicketSystem.repository.SeatClassRepository;
import com.example.MaupinAirlineTicketSystem.repository.UserPromotionRepository;
import com.example.MaupinAirlineTicketSystem.repository.UserRepository;
import com.example.MaupinAirlineTicketSystem.dto.PendingPassenger;
import com.example.MaupinAirlineTicketSystem.entity.Booking;
import com.example.MaupinAirlineTicketSystem.entity.FlightPlan;
import com.example.MaupinAirlineTicketSystem.entity.Payment;
import com.example.MaupinAirlineTicketSystem.entity.Promotion;
import com.example.MaupinAirlineTicketSystem.entity.SeatClass;
import com.example.MaupinAirlineTicketSystem.entity.User;
import com.example.MaupinAirlineTicketSystem.service.BookingService;
import com.example.MaupinAirlineTicketSystem.service.PaymentService;
import com.example.MaupinAirlineTicketSystem.service.ReviewService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/airline")
public class PaymentController {

    @Autowired private PaymentService paymentService;
    @Autowired private BookingService bookingService;
    @Autowired private BookingRepository bookingRepository;
    @Autowired private FlightPlanRepository flightPlanRepository;
    @Autowired private SeatClassRepository seatClassRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private ReviewService reviewService;
    @Autowired private UserPromotionRepository promotionRepository;

	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private ReviewService reviewService;

	@GetMapping("/payment/{id}")
	public String paymentPage(
	        @PathVariable int id,
	        Model model){
    // Existing endpoint — keep for viewing/retrying payment on an
    // ALREADY-created booking (e.g. resuming a Pending booking).
    // Unchanged from before.
    @GetMapping("/payment/{id}")
    public String paymentPage(@PathVariable int id, Model model) {
        // ... unchanged, exactly as you have it today
        return "userview/payment";
    }

    // NEW: pre-booking payment page, driven entirely by session data
    @GetMapping("/payment/new")
    public String newPaymentPage(HttpSession session, Model model) {

        Integer flightPlanId = (Integer) session.getAttribute("pendingFlightPlanId");
        Integer passengers = (Integer) session.getAttribute("pendingPassengers");
        String seatClass = (String) session.getAttribute("pendingSeatClass");

        if (flightPlanId == null || passengers == null || seatClass == null) {
            return "redirect:/airline/flights";
        }

        FlightPlan flightPlan = flightPlanRepository.findById(flightPlanId).orElseThrow();
        SeatClass seatClassEntity = seatClassRepository.findByClassNameIgnoreCase(seatClass);

        double originalPrice = flightPlan.getPrice() * seatClassEntity.getPriceMultiplier() * passengers;


	    // calculate original price
	    Booking booking = payment.getBooking();

	    double originalPrice =
	            booking.getFlightPlan().getPrice()
	            *
	            booking.getSeatClass().getPriceMultiplier()
	            *
	            booking.getPassengers();


	    model.addAttribute(
	            "originalPrice",
	            originalPrice
	    );

        Optional<Promotion> globalPromotion = promotionRepository
            .findFirstByStatusAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                "Active", LocalDateTime.now(), LocalDateTime.now());

        Promotion flightPlanPromotion = flightPlan.getPromotion();

        double totalDiscountPercent = 0;
        if (flightPlanPromotion != null) {
            totalDiscountPercent += flightPlanPromotion.getPercentage();
            model.addAttribute("flightPlanPromotion", flightPlanPromotion);
        }
        if (globalPromotion.isPresent()) {
            Promotion gp = globalPromotion.get();
            totalDiscountPercent += gp.getPercentage();
            model.addAttribute("globalPromotion", gp);
        }

        double finalPrice = originalPrice;
        if (totalDiscountPercent > 0) {
            finalPrice = originalPrice - (originalPrice * totalDiscountPercent / 100);
        }

        model.addAttribute("originalPrice", originalPrice);
        model.addAttribute("finalPrice", finalPrice);

        return "userview/payment";
    }

    @GetMapping("/paymentSuccess")
    public String paymentSuccess(
            @RequestParam int bookingId,
            @RequestParam(required = false) String success,
            Model model) {
        // unchanged
        Booking booking = bookingRepository.findById(bookingId).orElseThrow();
        boolean reviewed = reviewService.hasReview(bookingId);
        boolean isSuccess = success != null;

        model.addAttribute("booking", booking);
        model.addAttribute("reviewed", reviewed);
        model.addAttribute("success", isSuccess);

        return "userview/paymentSuccess";
    }

    // NEW: this is where the booking, passenger details, and payment
    // all actually get created — after payment proof is submitted.
    @PostMapping("/payment/upload")
    public String uploadPayment(
            @RequestParam MultipartFile screenshot,
            HttpSession session,
            Principal principal,
            RedirectAttributes redirectAttributes) {

        Integer flightPlanId = (Integer) session.getAttribute("pendingFlightPlanId");
        Integer passengers = (Integer) session.getAttribute("pendingPassengers");
        String seatClass = (String) session.getAttribute("pendingSeatClass");

        @SuppressWarnings("unchecked")
        List<PendingPassenger> passengerDetails =
            (List<PendingPassenger>) session.getAttribute("pendingPassengerDetails");

        if (flightPlanId == null || passengers == null || seatClass == null) {
            return "redirect:/airline/flights";
        }

        User user = userRepository.findByEmail(principal.getName());

        Booking booking;
        try {
            booking = bookingService.createBooking(
                flightPlanId, seatClass, passengers, user, passengerDetails);
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("bookingError", e.getMessage());
            return "redirect:/airline/flight/" + flightPlanId
                + "?passengers=" + passengers + "&seatClass=" + seatClass;
        }

        Payment payment = booking.getPayment();
        paymentService.uploadScreenshot(payment.getPaymentId(), screenshot);

        session.removeAttribute("pendingFlightPlanId");
        session.removeAttribute("pendingPassengers");
        session.removeAttribute("pendingSeatClass");
        session.removeAttribute("pendingPassengerDetails");

        return "redirect:/airline/paymentSuccess?bookingId=" + booking.getBookingId();
    }
}