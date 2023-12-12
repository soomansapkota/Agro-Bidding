package com.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.model.Product;
import com.demo.model.Retailer;
import com.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	Optional<User> findByUsername(String username);

    @Query(value = "SELECT * FROM f_product WHERE u_id = ?1", nativeQuery = true)
    List<User> findAllByU_id(int u_id);
}
