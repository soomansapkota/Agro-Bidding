package com.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.model.Farmer;
import com.demo.model.Retailer;
import com.demo.model.User;

@Repository
public interface RetailerRepository extends JpaRepository<Retailer, Integer> {

	Optional<Retailer> findByUserId(int u_id);
	
    @Query(value = "SELECT * FROM r_table", nativeQuery = true)
    List<Retailer> findAll();
	
}
