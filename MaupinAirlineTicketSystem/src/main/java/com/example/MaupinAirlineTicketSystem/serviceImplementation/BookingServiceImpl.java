package com.example.MaupinAirlineTicketSystem.serviceImplementation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MaupinAirlineTicketSystem.dto.PendingPassenger;
import com.example.MaupinAirlineTicketSystem.entity.Booking;
import com.example.MaupinAirlineTicketSystem.entity.BookingDetail;
import com.example.MaupinAirlineTicketSystem.entity.FlightPlan;
import com.example.MaupinAirlineTicketSystem.entity.Payment;
import com.example.MaupinAirlineTicketSystem.entity.Promotion;
import com.example.MaupinAirlineTicketSystem.entity.SeatClass;
import com.example.MaupinAirlineTicketSystem.entity.User;
import com.example.MaupinAirlineTicketSystem.repository.BookingDetailRepository;
import com.example.MaupinAirlineTicketSystem.repository.BookingRepository;
import com.example.MaupinAirlineTicketSystem.repository.FlightPlanRepository;
import com.example.MaupinAirlineTicketSystem.repository.PaymentRepository;
import com.example.MaupinAirlineTicketSystem.repository.SeatClassRepository;
import com.example.MaupinAirlineTicketSystem.repository.UserPromotionRepository;
import com.example.MaupinAirlineTicketSystem.service.BookingService;

import jakarta.transaction.Transactional;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired private BookingRepository bookingRepository;
    @Autowired private FlightPlanRepository flightPlanRepository;
    @Autowired private SeatClassRepository seatClassRepository;
    @Autowired private PaymentRepository paymentRepository;
    @Autowired private UserPromotionRepository promotionRepository;
    @Autowired private BookingDetailRepository bookingDetailRepository;   // new

    @Override
    public double calculateTotalPrice(FlightPlan flightPlan, SeatClass seatClass, int passengers) {
        return flightPlan.getPrice() * seatClass.getPriceMultiplier() * passengers;
    }

    private void validateSeats(FlightPlan flightPlan, String seatClass, int passengers) {
        if (passengers > flightPlan.getAvailableSeats()) {
            throw new RuntimeException("Not enough available seats on this flight");
        }
        if (passengers > flightPlan.getSeatsForClass(seatClass)) {
            throw new RuntimeException(
                "Not enough available seats. Only "
                + flightPlan.getSeatsForClass(seatClass)
                + " " + seatClass + " class seat(s) left on this flight"
            );
        }
    }

    @Override
    public void validateAvailability(int flightPlanId, String seatClass, int passengers) {
        FlightPlan flightPlan = flightPlanRepository.findById(flightPlanId).orElseThrow();
        validateSeats(flightPlan, seatClass, passengers);
    }

    @Override
    @Transactional
    public Booking createBooking(
            int flightPlanId,
            String seatClass,
            int passengers,
            User user,
            List<PendingPassenger> passengerDetails) {

        FlightPlan flightPlan = flightPlanRepository.findById(flightPlanId).orElseThrow();

        SeatClass seatClassEntity = seatClassRepository.findByClassNameIgnoreCase(seatClass);
        if (seatClassEntity == null) {
            throw new RuntimeException("Seat class not found");
        }

        validateSeats(flightPlan, seatClass, passengers);

        Booking booking = new Booking();
        booking.setFlightPlan(flightPlan);
        booking.setUser(user);
        booking.setSeatClass(seatClassEntity);
        booking.setBookingDate(LocalDateTime.now());
        booking.setStatus("PENDING");

        double total = flightPlan.getPrice() * seatClassEntity.getPriceMultiplier() * passengers;

        Promotion flightPlanPromotion = flightPlan.getPromotion();
        Optional<Promotion> globalPromotion = promotionRepository
            .findFirstByStatusAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                "Active", LocalDateTime.now(), LocalDateTime.now());

        double totalDiscountPercent = 0;
        if (flightPlanPromotion != null) {
            totalDiscountPercent += flightPlanPromotion.getPercentage();
            booking.setPromotion(flightPlanPromotion);
        }
        if (globalPromotion.isPresent()) {
            Promotion gp = globalPromotion.get();
            totalDiscountPercent += gp.getPercentage();
            if (flightPlanPromotion == null) {
                booking.setPromotion(gp);
            }
        }
        if (totalDiscountPercent > 0) {
            total = total - (total * totalDiscountPercent / 100);
        }

        booking.setPassengers(passengers);
        booking.setTotalAmount(total);

        Booking savedBooking = bookingRepository.save(booking);

        // Create passenger detail rows in the SAME transaction as the booking
        if (passengerDetails != null) {
            for (PendingPassenger p : passengerDetails) {
                BookingDetail detail = new BookingDetail();
                detail.setBooking(savedBooking);
                detail.setPassengerFirstName(p.getFirstName());
                detail.setPassengerLastName(p.getLastName());
                detail.setPassport(p.getPassport());
                detail.setDOB(p.getDob());
                bookingDetailRepository.save(detail);
            }
        }

        Payment payment = new Payment();
        payment.setAmount(total);
        payment.setPaymentStatus("WAITING");
        payment.setBooking(savedBooking);
        paymentRepository.save(payment);

        savedBooking.setPayment(payment);
        bookingRepository.save(savedBooking);

        return savedBooking;
    }
}