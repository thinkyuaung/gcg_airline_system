package com.example.MaupinAirlineTicketSystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.MaupinAirlineTicketSystem.component.LoginSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private LoginSuccessHandler successHandler;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http
			// 1. Disable CSRF for REST API endpoints
			.csrf(csrf -> csrf
				.ignoringRequestMatchers("/airline/api/**")
			)

			// 2. Configure URL Permissions
			.authorizeHttpRequests(auth -> auth

				.requestMatchers(
				    "/airline/",
				    "/airline/index",
				    "/airline/home",
				    "/airline/login",
				    "/airline/signup",
				    "/airline/booking",
				    "/airline/manage-booking",
				    "/airline/flights",
				    "/airline/packages",
				    "/airline/support",
				    "/airline/search",
				    "/airline/flightDetail/**",
				    "/airline/api/chat",     // <-- ADDED: Allow Chatbot API access
				    "/uploads/**",
				    "/css/**",
				    "/js/**",
				    "/images/**"
				)
				.permitAll()

			    .requestMatchers("/airline/admin/**")
			    .hasRole("ADMIN")

			    .requestMatchers(
			        "/airline/profile/**",
			        "/airline/dashboard",
			        "/airline/history/**",
			        "/airline/booking/**",
			        "/airline/payment/**"
			    )
			    .hasRole("USER")

			    .anyRequest().authenticated()
			)
			.formLogin(form -> form

				.loginPage("/airline/login")

				.loginProcessingUrl("/airline/login")

				.successHandler(successHandler)

				.failureUrl("/airline/login?error")

				.permitAll())

			.logout(logout -> logout

				.logoutUrl("/airline/logout")

				.logoutSuccessUrl("/airline/login")

				.invalidateHttpSession(true)

				.clearAuthentication(true)

				.deleteCookies("JSESSIONID")

				.permitAll())

			.httpBasic(Customizer.withDefaults());

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}