package com.demo.Controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.demo.Repository.BidRepository;
import com.demo.Repository.ProductRepository;
import com.demo.Repository.RetailerRepository;
import com.demo.Repository.UserRepository;
import com.demo.model.Product;
import com.demo.model.Retailer;
import com.demo.model.User;
import com.demo.model.bidmodel;
import com.demo.model.dto.BidRequestDTO;

import jakarta.servlet.http.HttpSession;

@Controller
public class BidController {

	@Autowired
	private BidRepository bidRepo;

	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private RetailerRepository retailerRepo;

	@Autowired
	private UserRepository userRepo;

	@GetMapping("/bidamount")
	public String bidding(Model model) {

		return "redirect:/bidmount";
	}

	@PostMapping("/bidamount/{id}")
	public String bidAmount(@ModelAttribute BidRequestDTO bidReqDTO,Model model, HttpSession session,@PathVariable int id) {
		try {
			Product product = new Product();
			Retailer retailer = new Retailer();
			bidmodel bid = new bidmodel();

//        retailer = retailerRepo.findById(bidReqDTO.getId()).get();
//       System.out.println(retailerRepo.findById(bidReqDTO.getId()).get());
			
			bid.setP_id(bidReqDTO.getP_id());
			System.out.println("What:"+bidReqDTO.getP_id());
			bid.setR_id(bidReqDTO.getR_id());
			System.out.println(bidReqDTO.getR_id());
			Optional<Integer> bidAmt1Optional = bidRepo.findHighestBidAmountByP_id(id);

			if (bidAmt1Optional.isPresent()) {
			    Integer bidAmt1 = bidAmt1Optional.get(); // Extract the Integer value from the Optional
			    Integer bidReqAmount = bidReqDTO.getBidamount(); // Assuming bidReqDTO.getBidamount() returns an Integer

			    System.out.println("Highest: " + bidAmt1);

			    if (bidReqAmount > bidAmt1) {
			        // If the input bid amount is greater, set it to the highest bid amount
			        bid.setBidamount(bidReqAmount);
			        bidRepo.save(bid);
			    } else {
			        model.addAttribute("alertMessage", "Please bid more than the previous one");
			    }
			} else {
			    // Handle the case where there are no previous bids
			    Integer bidReqAmount = bidReqDTO.getBidamount(); // Assuming bidReqDTO.getBidamount() returns an Integer
			    bid.setBidamount(bidReqAmount);
			    bidRepo.save(bid);
			}

			
			/*
			 * Integer bidAmt1 = bidRepo.findHighestBidAmountByP_id(id).get(); Integer
			 * Ammount = bidAmt1; System.out.println("QQQQQQQQQQ:" + Ammount);
			 * 
			 * 
			 * 
			 * 
			 * if ((Ammount == null) || (bidReqDTO.getBidamount() > bidAmt1)) {
			 * 
			 * bid.setBidamount(bidReqDTO.getBidamount()); bidRepo.save(bid); }
			 * 
			 * else { model.addAttribute("alertMessage",
			 * "Please Bid more than previous one"); }
			 */


			System.out.println("Bid: " + product.getP_id());
		} catch (Exception e) {

			e.printStackTrace();
		}

		return "redirect:/product/get/" + id;
	}

	@PostMapping("/accept/post/{p_id}/{bid_id}")
	public String farmerBidShow(@ModelAttribute BidRequestDTO bidReqDto, @PathVariable int p_id,
			@PathVariable int bid_id) {

		System.out.println("BID:" + p_id);
		bidRepo.updateByBid_id(bid_id);
		return "redirect:/fbid/get/" + p_id;
	}

	@GetMapping("/rbid")
	public String rbid(Model model, Principal principal) {

		String name = principal.getName();

//		System.out.println(name);

		User user = userRepo.findByUsername(name).get();
		int user1 = user.getU_id();

		System.out.println("Username : " + user1);

		Retailer retailerOptional = retailerRepo.findByUserId(user1).get();

		int retailer_id = retailerOptional.getId();

		System.out.println(retailer_id);

		System.out.println("Hey: " + retailerOptional);

		System.out.println("ID: " + retailerOptional.getId());

		List<bidmodel> bids =  bidRepo.findAllByR_id(retailer_id);
		
		

		/*
		 * List<Object> listData = new ArrayList<>();
		 * 
		 * for (bidmodel bid : bids) {
		 * 
		 * int bidAmt = bid.getBidamount(); String n = bid.getP_id().getPname();
		 * Map<String, String> map = new HashMap<>(); map.put(n,
		 * String.valueOf(bidAmt)); listData.add(map);
		 * 
		 * }
		 */
		model.addAttribute("bidList", bids);
		

		return "rbidded";
	}

}
