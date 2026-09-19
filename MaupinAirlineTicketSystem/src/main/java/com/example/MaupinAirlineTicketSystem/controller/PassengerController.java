package com.example.MaupinAirlineTicketSystem.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.MaupinAirlineTicketSystem.dto.PendingPassenger;
import com.example.MaupinAirlineTicketSystem.entity.Booking;
import com.example.MaupinAirlineTicketSystem.entity.User;
import com.example.MaupinAirlineTicketSystem.repository.BookingRepository;
import com.example.MaupinAirlineTicketSystem.repository.PaymentRepository;
import com.example.MaupinAirlineTicketSystem.repository.UserRepository;
import com.example.MaupinAirlineTicketSystem.service.BookingService;

import jakarta.servlet.http.HttpSession;

import com.example.MaupinAirlineTicketSystem.entity.BookingDetail;
import com.example.MaupinAirlineTicketSystem.repository.BookingDetailRepository;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/airline")
public class PassengerController {

    @Autowired private UserRepository userRepository;
    @Autowired private BookingService bookingService;

    @GetMapping("/passenger/new")
    public String passengerForm(
            HttpSession session,
            Model model,
            Principal principal,
            RedirectAttributes redirectAttributes) {

        Integer flightPlanId = (Integer) session.getAttribute("pendingFlightPlanId");
        Integer passengerCount = (Integer) session.getAttribute("pendingPassengers");
        String seatClass = (String) session.getAttribute("pendingSeatClass");

        if (flightPlanId == null || passengerCount == null || seatClass == null) {
            return "redirect:/airline/flights";
        }

        // Early availability check so the user isn't told "no seats" only after
        // filling out the whole passenger form
        try {
            bookingService.validateAvailability(flightPlanId, seatClass, passengerCount);
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("bookingError", e.getMessage());
            return "redirect:/airline/flight/" + flightPlanId
                + "?passengers=" + passengerCount + "&seatClass=" + seatClass;
        }

        User user = userRepository.findByEmail(principal.getName());

        model.addAttribute("passengerCount", passengerCount);
        model.addAttribute("user", user);
        model.addAttribute("activeTab", "/");

        return "userview/passengerForm";
    }

    @PostMapping("/passenger/save")
    public String savePassenger(
            @RequestParam List<String> firstName,
            @RequestParam List<String> lastName,
            @RequestParam List<String> passport,
            @RequestParam List<LocalDate> dob,
            HttpSession session) {

        Integer flightPlanId = (Integer) session.getAttribute("pendingFlightPlanId");
        Integer passengerCount = (Integer) session.getAttribute("pendingPassengers");
        String seatClass = (String) session.getAttribute("pendingSeatClass");

        if (flightPlanId == null || passengerCount == null || seatClass == null) {
            return "redirect:/airline/flights";
        }

        List<PendingPassenger> details = new ArrayList<>();

        for (int i = 0; i < firstName.size(); i++) {
            PendingPassenger p = new PendingPassenger();
            p.setFirstName(firstName.get(i));
            p.setLastName(lastName.get(i));
            p.setPassport(passport.get(i));
            p.setDob(dob.get(i));
            details.add(p);
        }

        session.setAttribute("pendingPassengerDetails", details);

        return "redirect:/airline/payment/new";
    }
}