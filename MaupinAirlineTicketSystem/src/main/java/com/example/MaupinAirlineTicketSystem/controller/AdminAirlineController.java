package com.example.MaupinAirlineTicketSystem.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.MaupinAirlineTicketSystem.entity.Airline;
import com.example.MaupinAirlineTicketSystem.service.AdminFlightService;

@Controller
@RequestMapping("/airline")
public class AdminAirlineController {
	
	@Autowired
	AdminFlightService adminFlightService;

	@GetMapping("/admin/airlineForm")
	public String create(Model model) {
		// just to show form and empty student object

		Airline a = new Airline();
		model.addAttribute("airline", a);

		return "Admin/AddAirline";
	}
	
	//No photo
//	@PostMapping("/students")
//	public String saveStudents(@Valid @ModelAttribute("student") Student s,BindingResult result) {
//		// @ModelAttr is to catch form data
//		if(result.hasErrors()) {
//			return "create_student";
//		}
//		studentService.saveStudent(s);
//		return "redirect:/student/students";
//	}
	
	@PostMapping("/admin/airline")
	public String saveSAirline(@ModelAttribute("airline") Airline airline,
			@RequestParam("photoFile") MultipartFile photoFile) throws IllegalStateException, IOException {
		
		//////Photo saved in uploads but in db, only photoName
		if(!photoFile.isEmpty()) {
			
			String uploadDir = System.getProperty("user.dir")+File.separator+"uploads"+File.separator;
			
			//file created with uploadDir
			File directory = new File(uploadDir);
			
			if(!directory.exists()) {
				directory.mkdir();
			}
			
			//create unique filename with timestamp
			String fileName = System.currentTimeMillis()+"_"+photoFile.getOriginalFilename();
			
			File destination = new File(directory,fileName);
			photoFile.transferTo(destination); //save file
			
			airline.setLogo(fileName);
		}
		
		/////
		adminFlightService.saveFlight(airline);
		
		return "redirect:/airline/admin";
	}
}
