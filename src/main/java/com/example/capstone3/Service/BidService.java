package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Auction;
import com.example.capstone3.Model.Bid;
import com.example.capstone3.Model.Customer;
import com.example.capstone3.Repository.AuctionRepository;
import com.example.capstone3.Repository.BidRepository;
import com.example.capstone3.Repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BidService {
    private final BidRepository bidRepository;
    private final CustomerRepository customerRepository;
    private final AuctionRepository auctionRepository;
    public List<Bid>getBid(){
        return bidRepository.findAll();
    }

    // Relation with auction
//    public void addBid(Bid bid){
//        bidRepository.save(bid);
//    }


public void bidding(Integer auctionId, Integer bidId, Integer customerId, Double amount) {
    Bid bid = bidRepository.findBidById(bidId);
    Customer customer = customerRepository.findCustomerById(customerId);
    Auction auction = auctionRepository.findAuctionsById(auctionId);

    if (bid == null) {
        throw new ApiException("Bid not found");
    }
    if (customer == null) {
        throw new ApiException("Customer not found");
    }
    if (auction == null) {
        throw new ApiException("Auction not found");
    }

    if (!"open".equalsIgnoreCase(bid.getStatus())) {
        throw new ApiException("Bid is not open");
    }

    if (bid.getExpirationDate() != null && bid.getExpirationDate().isBefore(LocalDateTime.now())) {
        throw new ApiException("Bid has expired");
    }

    if (amount < auction.getStartingBid()) {
        throw new ApiException("Bid amount is less than starting bid");
    }

    bid.getCustomers().add(customer);
    // Set the bid
    bid.setAmount(amount);
    bid.setAuction(auction);

    bidRepository.save(bid);
}


    public void addBidWithAuction(Bid bid, Integer auctionId) {
        Double maxAmount = bidRepository.findMaxBidAmountByAuctionId(auctionId);
        if (maxAmount != null && bid.getAmount() <= maxAmount) {
            throw new ApiException("The auction must be higher than previous offers.");
        }

        Auction auction = auctionRepository.findAuctionsById(auctionId);
        bid.setAuction(auction);
        bid.setStatus("open");
        bid.setBid_time(LocalDateTime.now());
        bidRepository.save(bid);
    }

    // Relation with customer
    public void assignBidToCustomer(Integer idCustomer,Integer idBid){
        Customer customer=customerRepository.findCustomerById(idCustomer);
        Bid bid=bidRepository.findBidById(idBid);
        if(customer==null || bid==null){
            throw new ApiException("Can't assign");
        }
        bid.getCustomers().add(customer);
        // customer
        customer.getBids().add(bid);
        bidRepository.save(bid);
        customerRepository.save(customer);
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
    public void CancelOfBid(Integer id){
        Bid bid=bidRepository.findBidById(id);
        if(bid==null){
            throw new ApiException("Bid Not Found");
        }
        bidRepository.delete(bid);
    }

    public void checkAndCloseExpiredBids() {
        List<Bid> bids = bidRepository.findAll();
        for (Bid bid : bids) {
            if (bid.getExpirationDate() != null &&
                    bid.getExpirationDate().isBefore(LocalDateTime.now()) &&
                    bid.getStatus().equals("open")) {
                bid.setStatus("closed");
                bidRepository.save(bid);
            }
        }
    }

    public List<Bid> getBidsExpiringSoon() {
        List<Bid> bids = bidRepository.findAll();
        List<Bid> soonExpiring = new ArrayList<>();

        for (Bid bid : bids) {
            if (bid.getExpirationDate() != null &&
                    bid.getStatus().equals("open")) {
                Duration duration = Duration.between(LocalDateTime.now(), bid.getExpirationDate());
                if (duration.toHours() <= 6 && duration.toMinutes() > 0) {
                    soonExpiring.add(bid);
                }
            }
        }
        return soonExpiring;
    }
    public void closeAuctionAndSelectWinner(Integer auctionId,Integer customerId) {
        List<Bid> bids = bidRepository.findAll();
        Customer customer=customerRepository.findCustomerById(customerId);
        Double maxAmount = bidRepository.findMaxBidAmountByAuctionId(auctionId);
        for (Bid bid : bids) {
            if (bid.getAuction().getId().equals(auctionId)) {
                if (bid.getAmount().equals(maxAmount)) {
                    String to = customer.getEmail();
                    String subject = "Contract Cancellation Notice";
                    String body = "Dear " + customer.getName() + ",\n\nYour Winner #" + customer.getId() + ".\n\nRegards,\nReal Estate Team";

//                    emailService.sendEmail(to, subject, body);
bid.setStatus("closed");
                }
                bidRepository.save(bid);
            }
        }
    }





}
// assign Not Add