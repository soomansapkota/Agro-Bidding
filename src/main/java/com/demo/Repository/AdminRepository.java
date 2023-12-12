package com.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.model.Admin;
import com.demo.model.Product;



public interface AdminRepository extends JpaRepository<Admin, Integer> {
	Admin findByUsername(String username);
	
    @Query(value = "SELECT * FROM f_agro_bidding", nativeQuery = true)
    List<Admin> findAll();
}
