package com.example.MaupinAirlineTicketSystem.serviceImplementation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MaupinAirlineTicketSystem.entity.Booking;
import com.example.MaupinAirlineTicketSystem.entity.Review;
import com.example.MaupinAirlineTicketSystem.repository.BookingRepository;
import com.example.MaupinAirlineTicketSystem.repository.ReviewRepository;
import com.example.MaupinAirlineTicketSystem.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService{

	@Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private BookingRepository bookingRepository;
    
	@Override
	public Review saveReview(int bookingId, int rating, String comment) {
		// TODO Auto-generated method stub
		 Booking booking = bookingRepository
	                .findById(bookingId)
	                .orElseThrow();

	        Review review = new Review();

	        review.setBooking(booking);

	        review.setUser(
	                booking.getUser()
	        );

	        review.setRating(rating);

	        review.setComment(comment);

	        review.setReviewDate(
	                LocalDateTime.now()
	        );

	        return reviewRepository.save(review);
	    }

	@Override
	public List<Review> getReviews() {
		// TODO Auto-generated method stub
		return reviewRepository.findAll();
	}

}
