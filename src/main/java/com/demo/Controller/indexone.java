package com.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexone {
	@GetMapping("/")
	public String Indexone() {
		
		return "index1";
	}
	
	@GetMapping("/home1")
	public String gotoHome() {
		
		return"index1";
	}
	
	@GetMapping("/contact1")
	public String doContact() {
		
		return"contact1";
	}
	
	@GetMapping("/about1")
	public String aboutone() {
		
		return"about1";
	}

}


