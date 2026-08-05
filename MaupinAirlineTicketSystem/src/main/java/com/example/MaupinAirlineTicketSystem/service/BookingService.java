package com.example.MaupinAirlineTicketSystem.service;

import java.util.List;

import com.example.MaupinAirlineTicketSystem.dto.PendingPassenger;
import com.example.MaupinAirlineTicketSystem.entity.Booking;
import com.example.MaupinAirlineTicketSystem.entity.FlightPlan;
import com.example.MaupinAirlineTicketSystem.entity.SeatClass;
import com.example.MaupinAirlineTicketSystem.entity.User;

public interface BookingService {

    double calculateTotalPrice(FlightPlan flightPlan, SeatClass seatClass, int passengers);

    void validateAvailability(int flightPlanId, String seatClass, int passengers);

    Booking createBooking(
        int flightPlanId,
        String seatClass,
        int passengers,
        User user,
        List<PendingPassenger> passengerDetails
    );
}
