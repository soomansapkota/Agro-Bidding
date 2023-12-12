package com.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.Repository.ProductRepository;
import com.demo.model.Product;
@Service
public class ProductServiceImpl implements ProductService{

	
	@Autowired
	private ProductRepository productRepo;
	
	
	
	public ProductServiceImpl(ProductRepository productRepo) {
		super();
		this.productRepo = productRepo;
	}



	@Override
	public List<Product> getAllProduct() {
		
		return productRepo.findAll();
	}



	
	}




