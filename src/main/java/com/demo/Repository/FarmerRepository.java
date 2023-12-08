package com.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.model.Admin;
import com.demo.model.Farmer;
import com.demo.model.Retailer;


public interface FarmerRepository extends JpaRepository<Farmer, Integer>{

	Optional<Farmer> findByUserId(int u_id);
 
    @Query(value = "SELECT * FROM f_agro_bidding", nativeQuery = true)
    List<Farmer> findAll();

}
