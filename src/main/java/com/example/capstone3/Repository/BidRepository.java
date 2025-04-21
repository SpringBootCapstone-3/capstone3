package com.example.capstone3.Repository;

import com.example.capstone3.Model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidRepository extends JpaRepository<Bid,Integer> {
    Bid findBidById(Integer id);
}
