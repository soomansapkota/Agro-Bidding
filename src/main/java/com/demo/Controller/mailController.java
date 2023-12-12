package com.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class mailController {
	
	
	@Autowired
    private JavaMailSender javaMailSender;
	
	@PostMapping("/send")
	public String postMail(@RequestParam String name, @RequestParam String email,@RequestParam String message ) {
		
		sendEmail(name,email,message);
		return"index";
	}
	
	
	private void sendEmail(String name, String email, String message) {
	    SimpleMailMessage msg = new SimpleMailMessage();
	    msg.setTo(email); 

	    msg.setSubject(name); 
	    msg.setText(message);

	    javaMailSender.send(msg);
	}


}
