package com.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

public class aboutController {
	

		
		@GetMapping("/about1")
		public String aboutone() {
			
			return"about1";
		
		}
}
