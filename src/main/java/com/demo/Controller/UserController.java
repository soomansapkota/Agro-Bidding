package com.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.demo.Repository.FarmerRepository;
import com.demo.model.Farmer;

@Controller

public class UserController {
	
	@Autowired
	private FarmerRepository userrepo;
	
	@PostMapping("/submit")
	public String savedata(@ModelAttribute Farmer user , Model model) {
		model.addAttribute("category",user.getCategory());
		userrepo.save(user);
		
		return"login";
	}

}