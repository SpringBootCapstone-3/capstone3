package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.DTO.AuctionDTO;
import com.example.capstone3.Model.Auction;
import com.example.capstone3.Model.Property;
import com.example.capstone3.Repository.AuctionRepository;
import com.example.capstone3.Repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuctionService {

    private final AuctionRepository auctionRepository;
    private final PropertyRepository propertyRepository;



    public List<Auction> getAllAuction(){
        return auctionRepository.findAll();
    }


    public void addAuction(AuctionDTO auctionDTO){
        Property property=propertyRepository.findPropertyById(auctionDTO.getProperaty_id());
        if(property==null){
            throw new ApiException("property");
        }

        Auction auction=new Auction(null,auctionDTO.getStartTime(),auctionDTO.getEndTime(),auctionDTO.getIsActive(),auctionDTO.getStartingBid(),auctionDTO.getStatus(),property,null);
        auctionRepository.save(auction);
    }


    public void updateAuction(AuctionDTO auctionDTO){
        Auction auction =auctionRepository.findAuctionsById(auctionDTO.getProperaty_id());
        if(auction==null){
            throw new ApiException("Auction is not found");
        }
        auction.setStartTime(auctionDTO.getStartTime());
        auction.setEndTime(auctionDTO.getEndTime());
        auction.setIsActive(auctionDTO.getIsActive());

        auctionRepository.save(auction);
    }


    public void deleteAuction(Integer id){
        Auction auction=auctionRepository.findAuctionsById(id);

        if(auction==null){
            throw new ApiException("Auction is not found");
        }
        auctionRepository.delete(auction);
    }

    public Auction getAuctionById(Integer id){
        return auctionRepository.findAuctionsById(id);
    }

    public List<Auction> getMostPopularAuctions() {
        return auctionRepository.findTop2ByOrderByBidsAmountDesc();
    }
}