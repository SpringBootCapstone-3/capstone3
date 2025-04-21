package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Auction;
import com.example.capstone3.Repository.AuctionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuctionService {

    private final AuctionRepository auctionRepository;



    public List<Auction> getAllAuction(){
        return auctionRepository.findAll();
    }


    public void addAuction(Auction auction){
        auctionRepository.save(auction);
    }


    public void updateAuction(Integer id,Auction auction){
        Auction oldAuction=auctionRepository.findAuctionsById(id);
        if(oldAuction==null){
            throw new ApiException("Auction is not found");
        }
        oldAuction.setStartTime(auction.getStartTime());
        oldAuction.setEndTime(auction.getEndTime());
        oldAuction.setIsActive(auction.getIsActive());

        auctionRepository.save(oldAuction);
    }


    public void deleteAuction(Integer id){
        Auction auction=auctionRepository.findAuctionsById(id);

        if(auction==null){
            throw new ApiException("Auction is not found");
        }
        auctionRepository.delete(auction);
    }
}
