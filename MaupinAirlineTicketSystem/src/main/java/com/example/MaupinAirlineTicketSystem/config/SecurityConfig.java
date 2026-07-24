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

		.authorizeHttpRequests(auth -> auth


						// Public pages
						.requestMatchers("/airline/", "/airline/index", "/airline/home", "/airline/login",
								"/airline/signup", "/css/**", "/js/**", "/images/**")
						.permitAll()

				// Public pages
				.requestMatchers(
				    "/airline/",
				    "/airline/index",
				    "/airline/home",
				    "/airline/login",
				    "/airline/signup",
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

						// Login page
						.loginPage("/airline/login")

						// Spring Security processes this POST request
						.loginProcessingUrl("/airline/login")

						// Redirect after successful login
						.successHandler(successHandler)

						// Redirect after failed login
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