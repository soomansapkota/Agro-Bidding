package com.demo.model.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class AdminRequestDTO {


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private int id;
	
	private String username;
	
	private String password;
}
