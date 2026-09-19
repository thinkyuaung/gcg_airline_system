package com.example.MaupinAirlineTicketSystem.service;

import java.util.List;

import com.example.MaupinAirlineTicketSystem.entity.Review;

public interface ReviewService {

    Review saveReview(
            int bookingId,
            int rating,
            String comment
    );

    List<Review> getReviews();

    List<Review> getLatestReviews();

    boolean hasReview(int bookingId);

}