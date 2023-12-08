package com.demo.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.Repository.RetailerRepository;
import com.demo.Repository.UserRepository;
import com.demo.model.Farmer;
import com.demo.model.Product;
import com.demo.model.Retailer;
import com.demo.model.User;
import com.demo.model.UserRole;
import com.demo.model.dto.RetailerRequestDTO;
import com.demo.utils.VerifyRecaptcha;

import jakarta.servlet.http.HttpSession;

@Controller
public class retailerController {
	
	@Autowired
	private RetailerRepository retailerRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("/retailer")
	public String showloginR() {
		
		return"retailerLogin";
	}
	
	
	@GetMapping("/rsignup")
	public String doLoginR() {
		
		return"retailerSignup";
	}
	
	@GetMapping("/rlogin")
	public String showLogin() {
		
		return"retailerLogin";
	}
	
	
//	@PostMapping("/rlogin")
//	public String doLogin(@ModelAttribute Retailer retailer,Model model,@RequestParam("g-recaptcha-response") String reCode )throws IOException {
//	retailer.setPassword(DigestUtils.md5DigestAsHex(retailer.getPassword().getBytes()));
//		
//	if(	VerifyRecaptcha.verify(reCode)) {
//	Retailer retailers =retailerRepo.findByUsernameAndPassword(retailer.getUsername(),retailer.getPassword()); 
//
//		if(retailers!=null) {
//
//	        
////			session.setAttribute("r_id",retailers.getId());
////			session.setAttribute("r_username",retailers.getUsername());
//		model.addAttribute("uname",retailer.getUsername());
//		
//			return "index";
//		}else {
//		model.addAttribute("message","user not found!!");
//		return"retailerLogin";
//		}
//	}
//		model.addAttribute("message","Are you a robot!!");
//		return "retailerlogin";
//	}
	
	@GetMapping("/rdashboard")
	public String retailerDashboard()
	{
	return "rbidded"	;
	}

	


	@PostMapping("/rsignup")
	public String doSignupRetailer(@ModelAttribute RetailerRequestDTO retailerDTO) {
		BCryptPasswordEncoder bcryptPassword = new BCryptPasswordEncoder();
		User user = new User();
		Retailer retailer = new Retailer();
		
		user.setName(retailerDTO.getName());
		user.setUsername(retailerDTO.getUsername());
		user.setAddress(retailerDTO.getAddress());
		user.setPassword(bcryptPassword.encode(retailerDTO.getPassword()));
		user.setUserRole(UserRole.RETAILER);
		user = userRepo.save(user);
		
		retailer.setCnumber(retailerDTO.getCnumber());
		retailer.setPan(retailerDTO.getPan());
		retailer.setType(retailerDTO.getType());
		retailer.setUser(user);
		retailerRepo.save(retailer);
		return "retailerLogin";
	}
}