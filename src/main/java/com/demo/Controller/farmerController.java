package com.demo.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.Repository.BidRepository;
import com.demo.Repository.FarmerRepository;
import com.demo.Repository.ProductRepository;
import com.demo.Repository.UserRepository;
import com.demo.model.Farmer;
import com.demo.model.Product;
import com.demo.model.Retailer;
import com.demo.model.User;
import com.demo.model.UserRole;
import com.demo.model.bidmodel;
import com.demo.model.dto.FaremerRequestDTO;

import jakarta.servlet.http.HttpSession;




@Controller
public class farmerController {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private FarmerRepository farmRepo;
	
	@Autowired
	private ProductRepository producrRepo;
	
	@Autowired
	private BidRepository bidRepo;
	
	@GetMapping("/fsignup")
	public String farmerSign() {
		
		return"farmerSignup";
	}
	
	
	@PostMapping("/fsignup")
	public String saveFarmer(@ModelAttribute FaremerRequestDTO farmerDTO) {
		BCryptPasswordEncoder bcryptPassword = new BCryptPasswordEncoder();
		User user =new User();
		Farmer farmer = new Farmer();
		
		user.setName(farmerDTO.getName());
		user.setUsername(farmerDTO.getUsername());
		user.setAddress(farmerDTO.getAddress());
		user.setPassword(bcryptPassword.encode(farmerDTO.getPassword()));
		user.setUserRole(UserRole.FARMER);
		user = userRepo.save(user);
		
		farmer.setCategory(farmerDTO.getCategory());
		farmer.setCitizenshipnumber(farmerDTO.getCitizenshipnumber());
		farmer.setEmail(farmerDTO.getEmail());
		farmer.setPannumber(farmerDTO.getPannumber());
		farmer.setUser(user);
		
		farmRepo.save(farmer);
		return "retailerLogin";
	}
	
	

//		@PostMapping("/flogin")
//	public String doLogin(@ModelAttribute Farmer farmer, Model model, HttpSession session) {
//		Farmer farm =farmRepo.findBy(farmer.getEmail(),farmer.getCitizenshipnumber()); 
//		if (farm!=null) {
//			session.setAttribute("farmer", farm);
//			session.setMaxInactiveInterval(120);
//			model.addAttribute("uname",farmer.getCitizenshipnumber());
//			return"bootstrapheader";
//		}else {
//			model.addAttribute("message","user not found!!");
//		return"farmerLogin";
//		}
//	}		
	
	@GetMapping("/edit")
	   public String editProduct(@RequestParam Integer id,Model model) {
		   
	    	model.addAttribute("emodel",producrRepo.findById(id));
		   return"editForm";
	   }
	    @PostMapping("/update")
	    public String updateProduct(@ModelAttribute Product product) {
	    	
	    	
	    	
	    	producrRepo.save(product);
	    	
	    	return"redirect:/list";
	    }
	    
	    @GetMapping("/delete")
	    public String deleteProduct(@RequestParam Integer id) {
	        producrRepo.deleteById(id);
	        return "redirect:list";
	    }
	    
	    
	    
	    @GetMapping("/fbid/get/{id}")
	    public String farmerShowBid(Model model,@PathVariable int id)
	    {
	    	System.out.println(id);
	    	
	    	List<bidmodel> bids =  bidRepo.findAllByP_id(id);
			
			System.out.println(bids);
			
			List<Object> listData = new ArrayList<>();
		
			for (bidmodel bid : bids) {
				
				int bidAmt = bid.getBidamount();
				int n = bid.getBid_id();
				Map<String, String> map = new HashMap<>();
				map.put(String.valueOf(n), String.valueOf(bidAmt));
				listData.add(map);
				
			}
			
			
			model.addAttribute("p_id",id);
			
			model.addAttribute("bidList",bids);
			
			System.out.println("SNNN "+ bids);
			
			model.addAttribute("bids",listData);
			System.out.println("Suman :" + listData);
	    	
	    	
	    	return "farmerBidShown";
	    }



	    
	   
	  

}