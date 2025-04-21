package com.example.capstone3.Repository;

import com.example.capstone3.Model.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionRepository extends JpaRepository<Auction,Integer> {

    Auction findAuctionsById(Integer id);
}
