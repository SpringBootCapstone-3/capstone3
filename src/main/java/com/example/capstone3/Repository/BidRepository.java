package com.example.capstone3.Repository;

import com.example.capstone3.Model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BidRepository extends JpaRepository<Bid,Integer> {
    Bid findBidById(Integer id);

    @Query("SELECT MAX(b.amount) FROM Bid b WHERE b.auction.id = ?1")
    Double findMaxBidAmountByAuctionId(Integer auctionId);


}
