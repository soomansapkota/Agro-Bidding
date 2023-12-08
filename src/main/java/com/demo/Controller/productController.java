package com.demo.Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.Repository.FarmerRepository;
import com.demo.Repository.ProductRepository;
import com.demo.Repository.UserRepository;
import com.demo.Service.ProductService;
import com.demo.model.Farmer;
import com.demo.model.Product;
import com.demo.model.Retailer;
import com.demo.model.User;
import com.demo.model.bidmodel;

import jakarta.servlet.http.HttpSession;

@Controller
public class productController {

	@Autowired
	private ProductRepository producrRepo;

	@Autowired
	private ProductService productservice;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private FarmerRepository farmRepo;

	public productController(ProductService productservice) {
		super();
		this.productservice = productservice;
	}

	@PostMapping("/product")
	public String saveProduct(@RequestParam("file") MultipartFile file, @ModelAttribute Product product, Model model,
			RedirectAttributes redirectAttributes) throws IOException {

		if (!file.isEmpty()) {
			FileOutputStream fout = new FileOutputStream(
					"src/main/resources/static/productImages/" + file.getOriginalFilename());
			fout.write(file.getBytes());
			fout.close();

		}
		String filePath = file.getOriginalFilename();
		product.setImages(filePath);

		producrRepo.save(product);

		return "redirect:/table";
	}

	@GetMapping("/list")
	public String showProduct(Model model, HttpSession session, Product product, Principal principal) {

//		if(session.getAttribute("rlogin")==null) {
//		
//	return "farmerLogin";
//	
//    }
		String name = principal.getName();
//
		System.out.println(name);
//
		User user = userRepo.findByUsername(name).get();
		int user1 = user.getU_id();
//
		System.out.println("Username : " + user1);
//
		Farmer farmerOptional = farmRepo.findByUserId(user1).get();
//
		int farmer_id = farmerOptional.getId();
//		
		System.out.println(farmer_id);
//		
		System.out.println("Hey: " + farmerOptional);
//
		System.out.println("ID: " + farmerOptional.getId());
//
//		// System.out.println(retailer_id);
//
//		model.addAttribute("name", name);
//		model.addAttribute("r_id", retailer_id);
//
//		Product product1 = productRepo.findById(id).get();
//
//		File dir = new File("src/main/resources/static/productImages" + product1.getImages());
//		String[] imgname = dir.list();
//		model.addAttribute("imglist", imgname);
//		// List<Product> products = productRepo.findAll();
//		model.addAttribute("product", product1);
//		List<bidmodel> bids =  bidRepo.findAllByP_id(product1.getP_id());
//		List<Product> products = producrRepo.findAll();

		model.addAttribute("plist", producrRepo.findAllByFarmer_id(farmer_id));

		return "dashboard";
	}

	@GetMapping("/table")
	public String showtable(HttpSession session, Principal principal, Model model) {
//		if (session.getAttribute("rlogin")==null) {
//			return"rlogin";
		String name = principal.getName();

//		System.out.println(name);

		User user = userRepo.findByUsername(name).get();
		int user1 = user.getU_id();

		System.out.println("Username : " + user1);

		Farmer retailerOptional = farmRepo.findByUserId(user1).get();

		int farmer_id = retailerOptional.getId();

		System.out.println(farmer_id);

		System.out.println("Hey: " + retailerOptional);

		System.out.println("ID: " + retailerOptional.getId());

		// System.out.println(retailer_id);

		model.addAttribute("f_id", farmer_id);

//		}
		return "table";
	}

}
