package com.demo.Controller;

import java.io.File;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.demo.Repository.BidRepository;
import com.demo.Repository.ProductRepository;
import com.demo.Repository.RetailerRepository;
import com.demo.Repository.UserRepository;
import com.demo.model.Farmer;
import com.demo.model.Product;
import com.demo.model.Retailer;
import com.demo.model.User;
import com.demo.model.UserRole;
import com.demo.model.bidmodel;

import jakarta.servlet.http.HttpSession;

@Controller
public class indexController {

	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RetailerRepository retailerepo;
	
	@Autowired
	private BidRepository bidRepo;

	@GetMapping("/redirectTo")
	public String indexControllertwo(Principal principal) {
		// get user_id

		User user = userRepo.findByUsername(principal.getName()).get();
		// get_user_role
		// retailer -> redirect to its own page
		if (user.getUserRole().equals(UserRole.RETAILER)) {
			return "index";
		} else if (user.getUserRole().equals(UserRole.FARMER)) {
			return "dashboard";
		}
		 
		return null;

		// farmer -> redirect to its own page

	}

	@PreAuthorize("RETAILER")
	@GetMapping("/index")
	public String indexControllerone(Principal principal) {

		return "index1";

	}

	@GetMapping("/indexproduct")
	public String getIndexProducts(Model model, @ModelAttribute Product product, Principal principal) {
//		System.out.println("Princpal: " + principal.getName());
//		Retailer r = retailerepo.findByUsername(principal.getName()).get();
//		System.out.println(r.getId());

		File dir = new File("src/main/resources/static/productImages");
		String[] imgname = dir.list();
		model.addAttribute("imglist", imgname);
		List<Product> products = productRepo.findAll();
//	    
//	    for(Product productss : products) {
//	   
		model.addAttribute("productList", productRepo.findAll());
////    

		System.out.println(model.addAttribute("productList", productRepo.findAll()));
////
////

		return "products";
	}

//	
//	@GetMapping("/indexproduct")
//	public String getIndexProducts(Model model) {
//	    File dir = new File("src/main/resources/static/productImages");
//	    String[] imgname = dir.list();
//	    model.addAttribute("imglist", imgname);
//	    model.addAttribute("productList", productRepo.findAll());
//
//	    String[] productes = new String[2];
//	    productes[0] = "products";
//	    productes[1] = "productDeatils";
//	    model.addAttribute("productes", productes);
//
//	    return "products";
//	}

//
//	@GetMapping("/indexproduct")
//	public String[] getIndexProducts(Model model) {
//	    File dir = new File("src/main/resources/static/productImages");
//	String[] imgname = dir.list();
//	    model.addAttribute("imglist", imgname);
//	 model.addAttribute("productList", productRepo.findAll());
//	    String[] products = new String[2];
//	    products[0] = "products";
//	    products[1] = "productDeatils";
//	    return products;
//	}

//
//	
//	@GetMapping("/details")
//	public String showDetails() {
//		return "productDeatils";
//	}
//		
//	@GetMapping("/details")
//	public String showDetails(Model model,@ModelAttribute Product product) {
//	    File dir = new File("src/main/resources/static/productImages");
//	    String[] imgname = dir.list();
//	    model.addAttribute("imglist", imgname);
//	      model.addAttribute("name",product.getFarm());
//	
//		model.addAttribute("price",product.getCost());
//       return "productDeatils";
//		}

	@GetMapping("/product/get/{id}")
	public String showDetails(Model model, @ModelAttribute Product product, HttpSession session, Principal principal,
			@PathVariable int id) {

//		System.out.println("Princpal: " + principal.getName());

		String name = principal.getName();

//		System.out.println(name);

		User user = userRepo.findByUsername(name).get();
		int user1 = user.getU_id();

		System.out.println("Username : " + user1);

		Retailer retailerOptional = retailerepo.findByUserId(user1).get();

		int retailer_id = retailerOptional.getId();
		
		System.out.println(retailer_id);
		
		System.out.println("Hey: " + retailerOptional);

		System.out.println("ID: " + retailerOptional.getId());

		// System.out.println(retailer_id);

		model.addAttribute("name", name);
		model.addAttribute("r_id", retailer_id);

		Product product1 = productRepo.findById(id).get();

		File dir = new File("src/main/resources/static/productImages" + product1.getImages());
		String[] imgname = dir.list();
		model.addAttribute("imglist", imgname);
		// List<Product> products = productRepo.findAll();
		model.addAttribute("product", product1);
		List<bidmodel> bids =  bidRepo.findAllByP_id(product1.getP_id());
		
		System.out.println(bids);
		
		List<Object> listData = new ArrayList<>();
		
		for (bidmodel bid : bids) {
			
			int bidAmt = bid.getBidamount();
			String n = bid.getR_id().getUser().getName();
			Map<String, String> map = new HashMap<>();
			map.put(n, String.valueOf(bidAmt));
			listData.add(map);
		}
		
		
		
		model.addAttribute("bids",listData);
		System.out.println("Suman :" + listData);
		
//	    int id =  (int) session.getAttribute("r_id");
//	    model.addAttribute(id);

		return "productDeatils";
	}

	@GetMapping("/showheader")
	public String showHeader() {

		return "bootstrapheader";
	}

	@GetMapping("/logout")
	public String doLogout(HttpSession session) {
		session.invalidate();
		return "index1";
	}

}