package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Bid;
import com.example.capstone3.Repository.BidRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BidService {
    private final BidRepository bidRepository;
    public List<Bid>getBid(){
        return bidRepository.findAll();
    }
    public void addBid(Bid bid){
        bidRepository.save(bid);
    }
    public void updateBid(Integer id,Bid bid){
        Bid bid1=bidRepository.findBidById(id);
        if(bid1==null){
            throw new ApiException("Bid Not Found");
        }
        bid1.setAmount(bid.getAmount());
        bid1.setBid_time(bid.getBid_time());
        bidRepository.save(bid);
    }
    public void deleteBid(Integer id){
        Bid bid=bidRepository.findBidById(id);
        if(bid==null){
            throw new ApiException("Bid Not Found");
        }
        bidRepository.delete(bid);
    }
}
