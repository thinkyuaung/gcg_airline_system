package com.example.MaupinAirlineTicketSystem.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.MaupinAirlineTicketSystem.entity.Booking;
import com.example.MaupinAirlineTicketSystem.entity.User;
import com.example.MaupinAirlineTicketSystem.repository.BookingRepository;
import com.example.MaupinAirlineTicketSystem.repository.UserRepository;
import com.example.MaupinAirlineTicketSystem.service.BookingHistoryService;

@Service
public class BookingHistoryServiceImpl
implements BookingHistoryService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    private User getCurrentUser() {

        Authentication auth =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        return userRepository.findByEmail(auth.getName());
    }

    @Override
    public List<Booking> getAllBookings() {

        User user = getCurrentUser();

        return bookingRepository.findByUser_UserId(
                user.getUserId());
    }

    @Override
    public List<Booking> getUpcomingBookings() {

        User user = getCurrentUser();

        return bookingRepository
                .findByUser_UserIdAndStatusNot(
                        user.getUserId(),
                        "CANCELLED");
    }

    @Override
    public List<Booking> getCancelledBookings() {

        User user = getCurrentUser();

        return bookingRepository
                .findByUser_UserIdAndStatus(
                        user.getUserId(),
                        "CANCELLED");
    }

    @Override
    public Booking getBookingDetail(int id) {

        return bookingRepository
                .findById(id)
                .orElseThrow();
    }

}
