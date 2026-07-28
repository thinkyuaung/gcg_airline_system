package com.example.MaupinAirlineTicketSystem.config;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.MaupinAirlineTicketSystem.entity.SeatClass;
import com.example.MaupinAirlineTicketSystem.entity.User;
import com.example.MaupinAirlineTicketSystem.repository.SeatClassRepository;
import com.example.MaupinAirlineTicketSystem.repository.UserRepository;


@Configuration
public class DataInitializer {

	@Bean
	CommandLineRunner init(UserRepository repo, SeatClassRepository seatClassRepo, PasswordEncoder encoder) {

		return args -> {

			if (repo.findByEmail("admin@gmail.com") == null) {

				User admin = new User();

				admin.setFirstName("Admin");
				admin.setLastName("Account");
				admin.setPassport("ADMIN001");
				admin.setDob(LocalDate.of(1990, 1, 1));

				admin.setEmail("admin@gmail.com");

				admin.setPassword(encoder.encode("admin123"));

				admin.setPhoneNumber("0900000000");

				admin.setRole("ADMIN");

				admin.setStatus("active");

				repo.save(admin);

				System.out.println("Admin account created!");
			}

			if (repo.findByEmail("user@gmail.com") == null) {

				User user = new User();

				user.setFirstName("Test");
				user.setLastName("User");
				user.setPassport("USER001");
				user.setDob(LocalDate.of(2000, 1, 1));

				user.setEmail("user@gmail.com");

				user.setPassword(encoder.encode("user123"));

				user.setPhoneNumber("0911111111");

				user.setRole("USER");

				user.setStatus("active");

				repo.save(user);

				System.out.println("User account created!");
			}

//			if (seatClassRepo.count() == 0) {
//				seatClassRepo.save(new SeatClass(1, "Economy", 20, 1.0));
//				seatClassRepo.save(new SeatClass(2, "Business", 25, 2.0));
//				seatClassRepo.save(new SeatClass(3, "First Class", 30, 3.0));
//				System.out.println("Seat classes created!");
//			}

		};
	}
}
