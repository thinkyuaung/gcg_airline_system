package com.example.MaupinAirlineTicketSystem.serviceImplementation;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.example.MaupinAirlineTicketSystem.repository.UserRepository;
import com.example.MaupinAirlineTicketSystem.service.BookingService;

import jakarta.transaction.Transactional;

@Service
public class BookingServiceImpl implements BookingService{

	@Autowired
    private BookingRepository bookingRepository;


    @Autowired
    private FlightPlanRepository flightPlanRepository;


    @Autowired
    private SeatClassRepository seatClassRepository;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BookingDetailRepository bookingDetailRepository;
    
    @Autowired
    private UserPromotionRepository promotionRepository;
    
	@Override
	public double calculateTotalPrice(FlightPlan flightPlan, SeatClass seatClass, int passengers) {
		// TODO Auto-generated method stub
		return flightPlan.getPrice()
                *
                seatClass.getPriceMultiplier()
                *
                passengers;
	}


	@Override
	@Transactional
	public Booking createBooking(
	        int flightPlanId,
	        String seatClass,
	        int passengers,
	        User user) {


	    FlightPlan flightPlan =
	            flightPlanRepository
	            .findById(flightPlanId)
	            .orElseThrow();


	    // Convert String -> SeatClass Entity

	    SeatClass seatClassEntity =
	            seatClassRepository
	            .findByClassName(seatClass);



	    if(seatClassEntity == null){

	        throw new RuntimeException(
	            "Seat class not found"
	        );

	    }



	    Booking booking = new Booking();


	    booking.setFlightPlan(flightPlan);

	    booking.setUser(user);

	    booking.setSeatClass(seatClassEntity);


	    booking.setBookingDate(
	            LocalDateTime.now()
	    );


	    booking.setStatus("PENDING");



	    double total =
	            flightPlan.getPrice()
	            * seatClassEntity.getPriceMultiplier()
	            * passengers;

	    Optional<Promotion> promotion =
	            promotionRepository
	            .findFirstByStatusAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
	                    "Active",
	                    LocalDateTime.now(),
	                    LocalDateTime.now());

	    if (promotion.isPresent()) {

	        Promotion p = promotion.get();

	        total = total - (total * p.getPercentage() / 100);

	        booking.setPromotion(p);
	    }



	    booking.setPassengers(passengers);
	    booking.setTotalAmount(total);

	    Booking savedBooking = bookingRepository.save(booking);

	    Payment payment = new Payment();
	    payment.setAmount(total);
	    payment.setPaymentStatus("WAITING");
	    payment.setBooking(savedBooking);

	    paymentRepository.save(payment);

	    savedBooking.setPayment(payment);
	    bookingRepository.save(savedBooking);
	    BookingDetail detail = new BookingDetail();
	    detail.setBooking(savedBooking);
	    detail.setPassengerFirstName(user.getFirstName());
	    detail.setPassengerLastName(user.getLastName());
	    detail.setPassport(user.getPassport());
	    bookingDetailRepository.save(detail);

	    return savedBooking;

	}
}
