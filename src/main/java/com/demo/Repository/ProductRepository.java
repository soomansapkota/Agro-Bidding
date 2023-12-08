package com.demo.Repository;

import java.util.List;

import org.apache.el.stream.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.model.Product;
import com.demo.model.bidmodel;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	Product  findByPname(String Pname);
	
    @Query(value = "SELECT * FROM f_product WHERE p_id = ?1", nativeQuery = true)
    List<Product> findAllByP_id(int p_id);
	
    @Query(value = "SELECT * FROM f_product WHERE farmer_id = ?1", nativeQuery = true)
    List<Product> findAllByFarmer_id(int farmer_id);
}
