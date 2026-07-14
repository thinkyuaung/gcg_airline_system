package com.example.MaupinAirlineTicketSystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		String uploadPath = System.getProperty("user.dir")+"/uploads";
		
		//receive all photo types (jpg,png,...)
		registry.addResourceHandler("/photos/**")
		.addResourceLocations("file:"+uploadPath);
		
		//file mean all photos in uploads folder
	}
}
