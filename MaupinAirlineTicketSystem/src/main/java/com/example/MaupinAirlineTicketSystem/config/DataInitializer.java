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

            // SUPER ADMIN ACCOUNT
            if (repo.findByEmail("superadmin@gmail.com") == null) {

                User superAdmin = new User();

                superAdmin.setFirstName("Super");
                superAdmin.setLastName("Admin");
                superAdmin.setPassport("SUPERADMIN001");
                superAdmin.setDob(LocalDate.of(1985, 1, 1));

                superAdmin.setEmail("superadmin@gmail.com");
                superAdmin.setPassword(encoder.encode("superadmin123"));

                superAdmin.setPhoneNumber("0999999999");

                superAdmin.setRole("SUPERADMIN");
                superAdmin.setStatus("active");

                repo.save(superAdmin);

                System.out.println("Super Admin account created!");
            }


            // ADMIN ACCOUNT
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


            // USER ACCOUNT
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

        };
    }
}