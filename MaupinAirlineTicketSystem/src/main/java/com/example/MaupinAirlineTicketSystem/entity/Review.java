package com.example.MaupinAirlineTicketSystem.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="review")
public class Review {

	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private int reviewId;


	    private int rating;


	    @Column(length = 500)
	    private String comment;


	    private LocalDateTime reviewDate;



	    // User who wrote review
	    @ManyToOne
	    @JoinColumn(name="user_id")
	    private User user;



	    // Booking related to review
	    @ManyToOne
	    @JoinColumn(name="booking_id")
	    private Booking booking;



		public Review(int reviewId, int rating, String comment, LocalDateTime reviewDate, User user, Booking booking) {
			super();
			this.reviewId = reviewId;
			this.rating = rating;
			this.comment = comment;
			this.reviewDate = reviewDate;
			this.user = user;
			this.booking = booking;
		}



		public Review() {
			super();
			// TODO Auto-generated constructor stub
		}



		public int getReviewId() {
			return reviewId;
		}



		public void setReviewId(int reviewId) {
			this.reviewId = reviewId;
		}



		public int getRating() {
			return rating;
		}



		public void setRating(int rating) {
			this.rating = rating;
		}



		public String getComment() {
			return comment;
		}



		public void setComment(String comment) {
			this.comment = comment;
		}



		public LocalDateTime getReviewDate() {
			return reviewDate;
		}



		public void setReviewDate(LocalDateTime reviewDate) {
			this.reviewDate = reviewDate;
		}



		public User getUser() {
			return user;
		}



		public void setUser(User user) {
			this.user = user;
		}



		public Booking getBooking() {
			return booking;
		}



		public void setBooking(Booking booking) {
			this.booking = booking;
		}
	    
	    
	    
}
