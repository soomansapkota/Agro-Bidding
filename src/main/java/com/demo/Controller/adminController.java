
package com.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.Repository.AdminRepository;
import com.demo.Repository.FarmerRepository;
import com.demo.Repository.RetailerRepository;
import com.demo.Repository.UserRepository;
import com.demo.model.Admin;
import com.demo.model.Farmer;
import com.demo.model.Retailer;

@Controller
public class adminController {

	@Autowired
	private AdminRepository adminRepo;

	@Autowired
	private FarmerRepository farmRepo;
	
	@Autowired
	private RetailerRepository retailerRepo;
	
	@GetMapping("/admin")
	public String OpenAdmin() {
		return "admin";
	}

	/*
	 * @GetMapping("/admindash") public String login(String username, String
	 * password) { if (username.equals("admin") && password.equals("admin")) {
	 * 
	 * return "admindashboard"; } else {
	 * 
	 * return "admin"; } }
	 */

	@GetMapping("/admindash")
	public String login(String username, String password, Model model) {
		Admin user = adminRepo.findByUsername(username);

		System.out.println("Password :" + password);
		
		if (user != null && user.getPassword().equals(password)) {
			// Authentication successful, redirect to admin dashboard
			return "admindashboard";
		} 
		else {
			// Authentication failed, return to the login page with an error message
			model.addAttribute("errorMessage", "Invalid username or password");
			return "admin";
		}
		
	}

	@GetMapping("/ft")
	public String showFarmerTable(Model model) {

		List<Farmer> adm = farmRepo.findAll();
		
		model.addAttribute("add",adm);
		
		System.out.println("Faremr ::"+ adm);
		
		return "adFarmer";
	}
	
	@GetMapping("/rt")
	public String showRetailerTable(Model model) {

		List<Retailer> ret = retailerRepo.findAll();
		
		
		
		model.addAttribute("add",ret);
		
		System.out.println("Faremr ::"+ ret);
		
		return "adRetailer";
	}
	

	

}
