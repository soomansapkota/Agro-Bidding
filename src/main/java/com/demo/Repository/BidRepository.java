package com.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.model.Product;
import com.demo.model.bidmodel;

import jakarta.transaction.Transactional;

@Repository
public interface BidRepository extends JpaRepository<bidmodel, Integer> {
	
    @Query(value = "SELECT * FROM bid_tbl WHERE p_id = ?1", nativeQuery = true)
    List<bidmodel> findAllByP_id(int p_id);
    
    @Transactional
    @Modifying
    @Query(value = "UPDATE bid_tbl b SET b.status=true WHERE bid_id = ?1", nativeQuery = true)
    void updateByBid_id(int bid_id);
    
    @Query(value = "SELECT * FROM bid_tbl WHERE bid_id = ?1", nativeQuery = true)
    List<bidmodel> findAllByB_id(int bid_id);
    
    
    @Query(value = "SELECT * FROM bid_tbl WHERE r_id = ?1", nativeQuery = true)
    List<bidmodel> findAllByR_id(int bid_id);
    
    
    @Query(value= "SELECT b.bidamount FROM bid_tbl b WHERE p_id = ?1",nativeQuery = true)
    List<Integer> findBidAmountByP_id( int p_id);
    
    @Query(value= "SELECT MAX(b.bidamount) FROM bid_tbl b WHERE b.p_id = ?1",nativeQuery = true)
    Optional<Integer> findHighestBidAmountByP_id(int p_id);


}
