package com.demo.model.dto;

import com.demo.model.Product;
import com.demo.model.Retailer;

public class BidRequestDTO {

	private int id;
	private int bidamount;
	private Product p_id;
	private Retailer r_id;
	private boolean status;
	
	
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBidamount() {
		return bidamount;
	}
	public void setBidamount(int bidamount) {
		this.bidamount = bidamount;
	}
	public Product getP_id() {
		return p_id;
	}
	public void setP_id(Product p_id) {
		this.p_id = p_id;
	}
	public Retailer getR_id() {
		return r_id;
	}
	public void setR_id(Retailer r_id) {
		this.r_id = r_id;
	}

	
	
	
	
}
