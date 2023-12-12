package com.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class contactController {
	
	@GetMapping("/contact")
	public String doContact() {
		
		return"contact";
	}

}