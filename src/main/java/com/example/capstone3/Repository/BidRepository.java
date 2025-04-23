package com.example.capstone3.Repository;

import com.example.capstone3.Model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BidRepository extends JpaRepository<Bid,Integer> {
    Bid findBidById(Integer id);

    // Khalid
    @Query("SELECT MAX(b.amount) FROM Bid b WHERE b.auction.id = ?1")
    Double findMaxBidAmountByAuctionId(Integer auctionId);

    @Query("SELECT b FROM Bid b JOIN b.customers c WHERE c.id = ?1")
    List<Bid> findBidsByCustomerId(Integer customerId);


    //اعلى مزايده
    @Query("SELECT MAX(b.amount) FROM Bid b")
    Double findHighestBidAmount();
}
