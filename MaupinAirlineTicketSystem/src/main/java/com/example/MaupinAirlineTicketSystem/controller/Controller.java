package com.example.MaupinAirlineTicketSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.MaupinAirlineTicketSystem.repository.AirportRepository;
import com.example.MaupinAirlineTicketSystem.repository.SeatClassRepository;
import com.example.MaupinAirlineTicketSystem.entity.Booking;
import com.example.MaupinAirlineTicketSystem.entity.Promotion;
import com.example.MaupinAirlineTicketSystem.entity.User;
import com.example.MaupinAirlineTicketSystem.repository.AdminPromotionRepository;
import com.example.MaupinAirlineTicketSystem.repository.UserRepository;
import com.example.MaupinAirlineTicketSystem.service.BookingService;
import com.example.MaupinAirlineTicketSystem.service.ReviewService;

import jakarta.servlet.http.HttpSession;

@org.springframework.stereotype.Controller
@RequestMapping("/airline")
public class Controller {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AirportRepository airportRepository;

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private SeatClassRepository seatClassRepository;

	@Autowired
	private BookingService bookingService;

	@Autowired
	private AdminPromotionRepository promotionRepository;

	////////////// Home //////////////

	@GetMapping("/")

	public String home(Model model) {

		model.addAttribute("airports", airportRepository.findAll());

		model.addAttribute("seatClasses", seatClassRepository.findAll());

		List<Promotion> activePromotions = promotionRepository.findByStatus("Active");
		model.addAttribute("promotions", activePromotions);

		model.addAttribute("currentPage", "home");

		return "index";
	}

	public String index() {
		return "index";
	}

	@GetMapping("/index")
	public String index(Model model) {

		model.addAttribute("airports", airportRepository.findAll());

		model.addAttribute("seatClasses", seatClassRepository.findAll());

		List<Promotion> activePromotions = promotionRepository.findByStatus("Active");
		model.addAttribute("promotions", activePromotions);

		model.addAttribute("currentPage", "home");
		return "index";
	}

	@GetMapping("/about")
	public String about() {
		return "about";
	}

	@GetMapping("/support")
	public String support() {
		return "support";
	}
	////////////// Login //////////////

	@GetMapping("/login")
	public String loginPage(Model model) {

		model.addAttribute("user", new User());

		return "Login/login";
	}

	////////////// Signup //////////////

	@GetMapping("/signup")
	public String signupPage(Model model, HttpSession session) {

		model.addAttribute("user", new User());

		Integer pendingFlightPlanId = (Integer) session.getAttribute("pendingFlightPlanId");
		if (pendingFlightPlanId != null) {
			model.addAttribute("pendingFlightPlanId", pendingFlightPlanId);
			model.addAttribute("pendingPassengers", session.getAttribute("pendingPassengers"));
			model.addAttribute("pendingSeatClass", session.getAttribute("pendingSeatClass"));
		}

		return "Login/signup";
	}

	@PostMapping("/signup")
	public String signup(@ModelAttribute User user,
			@RequestParam(value = "pendingFlightPlanId", required = false) Integer pendingFlightPlanId,
			@RequestParam(value = "pendingPassengers", required = false) Integer pendingPassengers,
			@RequestParam(value = "pendingSeatClass", required = false) String pendingSeatClass, HttpSession session) {

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		user.setRole("USER");
		user.setStatus("active");

		userRepository.save(user);

		List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user.getEmail(), null,
				authorities);

		SecurityContext context = SecurityContextHolder.createEmptyContext();
		context.setAuthentication(authToken);
		SecurityContextHolder.setContext(context);
		session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context);

		session.setAttribute("loginUserName", user.getFirstName() + " " + user.getLastName());
		session.setAttribute("loginUserId", user.getUserId());
		session.setAttribute("loginUserRole", user.getRole());

		if (pendingFlightPlanId != null) {
			session.removeAttribute("pendingFlightPlanId");
			session.removeAttribute("pendingPassengers");
			session.removeAttribute("pendingSeatClass");

			Booking booking = bookingService.createBooking(pendingFlightPlanId, pendingSeatClass, pendingPassengers,
					user);

			return "redirect:/airline/payment/" + booking.getPayment().getPaymentId();
		}

		return "redirect:/airline/index";

	}

	////////////// User Profile //////////////

	@GetMapping("/profile")
	public String profile(Authentication authentication, Model model) {

		User user = userRepository.findByEmail(authentication.getName());

		if (user == null) {
			return "redirect:/airline/login";
		}

		model.addAttribute("user", user);

		return "User/profile";
	}

	////////////// Edit Profile //////////////

	@GetMapping("/profile/edit")
	public String editProfile(Authentication authentication, Model model) {

		User user = userRepository.findByEmail(authentication.getName());

		if (user == null) {
			return "redirect:/airline/login";
		}

		model.addAttribute("user", user);

		return "User/edit-profile";
	}

	@PostMapping("/profile/update")
	public String updateProfile(@ModelAttribute User user, @RequestParam String confirmPassword,
			Authentication authentication, Model model) {

		User loginUser = userRepository.findByEmail(authentication.getName());

		if (loginUser == null) {

			return "redirect:/airline/login";
		}

		if (!passwordEncoder.matches(confirmPassword, loginUser.getPassword())) {

			model.addAttribute("error", "Incorrect password.");

			model.addAttribute("user", loginUser);

			return "User/edit-profile";
		}

		user.setUserId(loginUser.getUserId());

		user.setPassword(loginUser.getPassword());

		user.setRole(loginUser.getRole());

		user.setStatus(loginUser.getStatus());

		if (user.getDob() == null) {

			user.setDob(loginUser.getDob());
		}

		userRepository.save(user);

		return "redirect:/airline/profile";
	}

	////////////// Change Password //////////////

	@GetMapping("/profile/change-password")
	public String changePasswordPage(Authentication authentication) {

		User user = userRepository.findByEmail(authentication.getName());

		if (user == null) {

			return "redirect:/airline/login";
		}

		return "User/change-password";
	}

	@PostMapping("/profile/update-password")
	public String updatePassword(

			@RequestParam String oldPassword,

			@RequestParam String newPassword,

			@RequestParam String confirmPassword,

			Authentication authentication,

			Model model

	) {

		User user = userRepository.findByEmail(authentication.getName());

		if (user == null) {

			return "redirect:/airline/login";
		}

		if (!passwordEncoder.matches(oldPassword, user.getPassword())) {

			model.addAttribute("error", "Old password is incorrect");

			return "User/change-password";
		}

		if (!newPassword.equals(confirmPassword)) {

			model.addAttribute("error", "New password does not match");

			return "User/change-password";
		}

		user.setPassword(passwordEncoder.encode(newPassword));

		userRepository.save(user);

		return "redirect:/airline/profile";
	}

	//////////// Admin Dashboard //////////////

	@GetMapping("/admin/dashboard")
	public String adminDashboard(Authentication authentication, Model model) {

		User loginUser = userRepository.findByEmail(authentication.getName());

		if (loginUser == null || !loginUser.getRole().equals("ADMIN")) {
			return "redirect:/airline/index";
		}

		model.addAttribute("users", userRepository.findAll());

		model.addAttribute("totalUsers", userRepository.count());

		model.addAttribute("activeUsers", userRepository.countByStatus("active"));

		model.addAttribute("inactiveUsers", userRepository.countByStatus("inactive"));

		model.addAttribute("adminUsers", userRepository.countByRole("ADMIN"));

		return "Admin/admin-dashboard";
	}

	////////// Dashboard Counts //////////////

	private void addDashboardCounts(Model model) {

		model.addAttribute("totalUsers", userRepository.count());

		model.addAttribute("activeUsers", userRepository.countByStatus("active"));

		model.addAttribute("inactiveUsers", userRepository.countByStatus("inactive"));

		model.addAttribute("adminUsers", userRepository.countByRole("ADMIN"));
	}

	////////////// All Users //////////////

	@GetMapping("/admin/users/all")
	public String allUsers(Model model) {

		model.addAttribute("users", userRepository.findAll());

		addDashboardCounts(model);

		return "Admin/admin-dashboard";
	}

	////////////// Active Users //////////////

	@GetMapping("/admin/users/active")
	public String activeUsers(Model model) {

		model.addAttribute("users", userRepository.findByStatus("active"));

		addDashboardCounts(model);

		return "Admin/admin-dashboard";
	}

	////////////// Inactive Users //////////////

	@GetMapping("/admin/users/inactive")
	public String inactiveUsers(Model model) {

		model.addAttribute("users", userRepository.findByStatus("inactive"));

		addDashboardCounts(model);

		return "Admin/admin-dashboard";
	}

	/////////// Admin Users //////////////

	@GetMapping("/admin/users/admin")
	public String adminUsers(Model model) {

		model.addAttribute("users", userRepository.findByRoleAndStatus("ADMIN", "active"));

		addDashboardCounts(model);

		return "Admin/admin-dashboard";
	}

	////////////// Delete User //////////////

	@GetMapping("/admin/user/delete/{id}")
	public String deleteUser(@PathVariable int id) {

		User user = userRepository.findById(id).orElse(null);

		if (user != null) {

			user.setStatus("inactive");

			userRepository.save(user);
		}

		return "redirect:/airline/admin/dashboard";
	}

	////////////// View User //////////////

	@GetMapping("/admin/users/view/{id}")
	public String viewUser(@PathVariable int id, Model model) {

		User user = userRepository.findById(id).orElse(null);

		if (user == null) {

			return "redirect:/airline/admin/dashboard";
		}

		model.addAttribute("user", user);

		return "Admin/admin-view-user";
	}

	//////////// Edit User ///////////////
	@GetMapping("/admin/users/edit/{id}")
	public String editUser(@PathVariable int id, Model model) {

	    User user = userRepository.findById(id).orElse(null);

	    if (user == null) {
	        return "redirect:/airline/admin/dashboard";
	    }

	    model.addAttribute("user", user);

	    return "Admin/admin-edit-user";
	}

	@PostMapping("/admin/users/update")
	public String updateUser(@ModelAttribute User user) {

		User existingUser = userRepository.findById(user.getUserId()).orElse(null);

		if (existingUser == null) {
			return "redirect:/airline/admin/dashboard";
		}
		user.setPassword(existingUser.getPassword());
		user.setRole(existingUser.getRole());
		user.setStatus(existingUser.getStatus());
		user.setDob(existingUser.getDob());

		userRepository.save(user);

		return "redirect:/airline/admin/dashboard";
	}

	////////////// Activate User //////////////

	@GetMapping("/admin/user/active/{id}")
	public String activeUser(@PathVariable int id) {

		User user = userRepository.findById(id).orElse(null);

		if (user != null) {

			user.setStatus("active");

			userRepository.save(user);
		}

		return "redirect:/airline/admin/dashboard";
	}

	/////////// Add User Page //////////////

	@GetMapping("/admin/add-user")
	public String addUserPage(Model model) {

		model.addAttribute("user", new User());

		return "Admin/add-user";
	}

	////////// Save User //////////////

	@PostMapping("/admin/add-user")
	public String saveUser(@ModelAttribute User user) {

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		user.setRole("USER");

		user.setStatus("active");

		userRepository.save(user);

		return "redirect:/airline/admin/dashboard";
	}

	/////////// Add Admin Page //////////////

	@GetMapping("/admin/add-admin")
	public String addAdminPage(Model model) {

		model.addAttribute("user", new User());

		return "Admin/add-admin";
	}

	/////////// Save Admin //////////////

	@PostMapping("/admin/add-admin")
	public String saveAdmin(@ModelAttribute User user) {

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		user.setRole("ADMIN");

		user.setStatus("active");

		userRepository.save(user);

		return "redirect:/airline/admin/dashboard";
	}
	////////////// Logout //////////////

	@GetMapping("/logout")
	public String logout(HttpSession session) {

		session.invalidate();

		return "redirect:/airline/";
	}

}