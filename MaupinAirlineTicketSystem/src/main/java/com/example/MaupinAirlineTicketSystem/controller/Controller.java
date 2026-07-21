package com.example.MaupinAirlineTicketSystem.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
 
import com.example.MaupinAirlineTicketSystem.entity.User;
import com.example.MaupinAirlineTicketSystem.repository.UserRepository;
 
import jakarta.servlet.http.HttpSession;
 
@org.springframework.stereotype.Controller
@RequestMapping("/airline") 
public class Controller {
 
	@Autowired
	private PasswordEncoder passwordEncoder;
 
	@Autowired
	private UserRepository userRepository;
 
	////////////// Home //////////////
 
	@GetMapping("/")
	public String index() {
		return "index";
	}
 
	@GetMapping("/index")
	public String home() {
		return "index";
	}

	@GetMapping("/flights")
	public String flights() {
		return "flights";
	}
	
	@GetMapping("/about")
	public String about() {
		return "about";
	}
      
	@GetMapping("/support")
	public String  support(){
		return "support";
	}
	////////////// Login //////////////
 
	@GetMapping("/login")
	public String loginPage(Model model) {
 
		model.addAttribute("user", new User());
 
		return "Login/loginPage";
	}
 
	////////////// Signup //////////////
 
	@GetMapping("/signup")
	public String signupPage(Model model) {
 
		model.addAttribute("user", new User());
 
		return "Login/signup";
	}
 
	@PostMapping("/signup")
	public String signup(@ModelAttribute User user) {
 
		user.setPassword(passwordEncoder.encode(user.getPassword()));
 
		user.setRole("USER");
		user.setStatus("active");
 
		userRepository.save(user);
 
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
 
		// Confirm current password
 
		if (!passwordEncoder.matches(confirmPassword, loginUser.getPassword())) {
 
			model.addAttribute("error", "Incorrect password.");
 
			model.addAttribute("user", loginUser);
 
			return "User/edit-profile";
		}
 
		user.setUserId(loginUser.getUserId());
 
		// keep old password
 
		user.setPassword(loginUser.getPassword());
 
		user.setRole(loginUser.getRole());
 
		user.setStatus(loginUser.getStatus());
 
		// keep old DOB if empty
 
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
 
		// check old password
 
		if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
 
			model.addAttribute("error", "Old password is incorrect");
 
			return "User/change-password";
		}
 
		// check new password
 
		if (!newPassword.equals(confirmPassword)) {
 
			model.addAttribute("error", "New password does not match");
 
			return "User/change-password";
		}
 
		user.setPassword(passwordEncoder.encode(newPassword));
 
		userRepository.save(user);
 
		return "redirect:/airline/profile";
	}
 
	////////////// Admin Dashboard //////////////
 
	@GetMapping("/admin/dashboard")
	public String adminDashboard(Authentication authentication, Model model) {
 
		User loginUser = userRepository.findByEmail(authentication.getName());
 
		if (loginUser == null || !loginUser.getRole().equals("ADMIN")) {
 
			return "redirect:/airline/home";
		}
 
		model.addAttribute("users", userRepository.findByStatus("active"));
 
		return "Admin/admin-dashboard";
	}
 
	////////////// All Users //////////////
 
	@GetMapping("/admin/users/all")
	public String allUsers(Model model) {
 
		model.addAttribute("users", userRepository.findAll());
 
		return "Admin/admin-dashboard";
	}
 
	////////////// Active Users //////////////
 
	@GetMapping("/admin/users/active")
	public String activeUsers(Model model) {
 
		model.addAttribute("users", userRepository.findByStatus("active"));
 
		return "Admin/admin-dashboard";
	}
 
	////////////// Inactive Users //////////////
 
	@GetMapping("/admin/users/inactive")
	public String inactiveUsers(Model model) {
 
		model.addAttribute("users", userRepository.findByStatus("inactive"));
 
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
 
	////////////// Logout //////////////
 
	@GetMapping("/logout")
	public String logout(HttpSession session) {
 
		session.invalidate();
 
		return "redirect:/airline/login";
	}
 
}