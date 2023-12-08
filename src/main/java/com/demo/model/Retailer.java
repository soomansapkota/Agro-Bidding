package com.demo.model;

import java.util.Collection;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="R_table")
public class Retailer {
	
	@Id
	@GeneratedValue
	private int r_id;
	private String type;
	
	private String pan;

	private String cnumber;
	

	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "u_id")
	private User user; 
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getId() {
		return r_id;
	}
	public void setId(int id) {
		this.r_id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}


	public String getCnumber() {
		return cnumber;
	}
	public void setCnumber(String cnumber) {
		this.cnumber = cnumber;
	}


	
	
	
	

}
