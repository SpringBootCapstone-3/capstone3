package com.example.capstone3.Repository;

import com.example.capstone3.Model.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Integer> {

    Auction findAuctionsById(Integer id);

    @Query("select a from Auction a where a.isActive = true and a.bids is empty ")
    List<Auction> findActiveAuctionsWithoutBids();

    @Query("SELECT count(a) FROM Auction a WHERE a.isActive = true")
    int countActiveAuctions();

    @Query("SELECT count(a) FROM Auction a WHERE a.isActive = false")
    int countEndedAuctions();

    List<Auction> findTop2ByOrderByBidsAmountDesc();


}
