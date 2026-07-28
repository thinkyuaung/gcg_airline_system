package com.example.MaupinAirlineTicketSystem.component;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		for (GrantedAuthority authority : authentication.getAuthorities()) {

			if ("ROLE_ADMIN".equals(authority.getAuthority())) {
				response.sendRedirect("/airline/admin/dashboard");
				return;
			}

			if ("ROLE_USER".equals(authority.getAuthority())) {

				 response.sendRedirect("/airline/dashboard");
				    return;

			   
			}
		}

		response.sendRedirect("/airline/login");
	}
}