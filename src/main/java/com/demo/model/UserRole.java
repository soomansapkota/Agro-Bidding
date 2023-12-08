package com.demo.model;

public enum UserRole {
	FARMER("FARMER"),
	RETAILER("RETAILER");
	
	UserRole(String value) {
		this.value = value;
	}

	private String value;

	public String getValue() {
		return this.value;
	}

	
}
