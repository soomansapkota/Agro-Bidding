package com.demo.model;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="bid_tbl")
public class bidmodel {

	@Id
	@GeneratedValue
	private int bid_id;
	
	private int bidamount;
	
	private boolean status;
	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "p_id")
	private Product p_id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "r_id")
	private Retailer r_id;
	

	public int getBid_id() {
		return bid_id;
	}

	public void setBid_id(int bid_id) {
		this.bid_id = bid_id;
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

	public int getBidamount() {
		return bidamount;
	}

	public void setBidamount(int bidamount) {
		this.bidamount = bidamount;
	}


}


