package com.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class profileController {
	
	
	@GetMapping("/profile")
	public String showProfile() {
		
		return"profile";
	}
	
	
	@GetMapping("/test")
	public String showTest() {
		
		return"bootstrapheader";
	}

}