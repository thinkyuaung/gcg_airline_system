package com.example.MaupinAirlineTicketSystem.component;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.MaupinAirlineTicketSystem.entity.User;
import com.example.MaupinAirlineTicketSystem.repository.UserRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		String email = authentication.getName();
		User user = userRepository.findByEmail(email);

		HttpSession session = request.getSession();
		if (user != null) {
			session.setAttribute("loginUserName", user.getFirstName() + " " + user.getLastName());
			session.setAttribute("loginUserId", user.getUserId());
			session.setAttribute("loginUserRole", user.getRole());
		}

		for (GrantedAuthority authority : authentication.getAuthorities()) {

			if ("ROLE_ADMIN".equals(authority.getAuthority())) {
				response.sendRedirect("/airline/admin");
				return;
			}
			else if ("ROLE_SUPERADMIN".equals(authority.getAuthority())) {
				response.sendRedirect("/airline/admin/dashboard");
				return;
			}
			else if ("ROLE_USER".equals(authority.getAuthority())) {
				if (session.getAttribute("pendingFlightPlanId") != null) {
					response.sendRedirect("/airline/booking/complete");
					return;
				}
				response.sendRedirect("/airline/");
				return;
			}
			else {
				response.sendRedirect("/airline/");
				return;
			}
		}
	}
}
