package com.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class dashboardController {
	
	@GetMapping("/dashboard")
	public String dashboardControllers() {
		
		return"bootstrapheader";
	}

}





