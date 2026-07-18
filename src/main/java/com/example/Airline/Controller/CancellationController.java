package com.example.Airline.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import com.example.Airline.entity.Booking;
import com.example.Airline.repository.BookingRepository;
import com.example.Airline.service.CancellationService;



@Controller
@RequestMapping("/airline")
public class CancellationController {


@Autowired
private BookingRepository bookingRepository;


@Autowired
private CancellationService cancellationService;




@GetMapping("/cancel/confirm/{id}")
public String confirmPage(
        @PathVariable int id,
        Model model){


    Booking booking =
            bookingRepository.findById(id)
            .orElseThrow();



    model.addAttribute(
            "booking",
            booking
    );


    return "userview/cancelConfirm";

}




@PostMapping("/cancel/{id}")
public String cancel(
        @PathVariable int id,
        @RequestParam String reason,
        Model model){



	try {

        cancellationService.cancelBooking(id, reason);

        return "userview/cancelSuccess";


    } catch(RuntimeException e){


        model.addAttribute(
            "message",
            e.getMessage()
        );


        return "userview/cancelError";

    }


}

}