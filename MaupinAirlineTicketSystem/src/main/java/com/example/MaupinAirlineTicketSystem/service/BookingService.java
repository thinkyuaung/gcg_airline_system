package com.example.MaupinAirlineTicketSystem.service;

import java.time.LocalDateTime;
import java.util.List;

import com.example.MaupinAirlineTicketSystem.dto.PendingPassenger;
import com.example.MaupinAirlineTicketSystem.entity.Booking;
import com.example.MaupinAirlineTicketSystem.entity.FlightPlan;
import com.example.MaupinAirlineTicketSystem.entity.SeatClass;
import com.example.MaupinAirlineTicketSystem.entity.User;

public interface BookingService {

    double calculateTotalPrice(FlightPlan flightPlan, SeatClass seatClass, int passengers);

    double getTimeBasedMultiplier(LocalDateTime departureTime);

    void validateAvailability(int flightPlanId, String seatClass, int passengers);

    Booking createBooking(
        int flightPlanId,
        String seatClass,
        int passengers,
        User user,
        List<PendingPassenger> passengerDetails
    );
}
