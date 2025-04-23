package com.example.capstone3.Controller;

import com.example.capstone3.Api.ApiResponse;
import com.example.capstone3.Model.Bid;
import com.example.capstone3.Service.BidService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/bid")
@AllArgsConstructor
public class BidController {

    private final BidService bidService;

    @GetMapping("/get")
    public ResponseEntity getAllBid() {
        return ResponseEntity.status(200).body(bidService.getBid());
    }

//    @PostMapping("/add")
//    public ResponseEntity addBid(@RequestBody @Valid Bid bid) {
//        bidService.addBid(bid);
//        return ResponseEntity.status(200).body(new ApiResponse("The Bid has been added successfully."));
//    }

    @PostMapping("/add/{auction_id}")
    public ResponseEntity addBidWithAuction(@PathVariable Integer auction_id, @RequestBody @Valid Bid bid) {
        bidService.addBidWithAuction(bid,auction_id);
        return ResponseEntity.status(200).body(new ApiResponse("The Bid has been added successfully."));
    }

    @PostMapping("/add/{idBid},{idCustomer}")
    public ResponseEntity assignBidToCustomer(@PathVariable Integer idBid, @PathVariable Integer idCustomer) {
        bidService.assignBidToCustomer(idCustomer, idBid);
        return ResponseEntity.status(200).body(new ApiResponse("The Bid has been add."));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateBid(@PathVariable Integer id, @RequestBody @Valid Bid bid) {
        bidService.updateBid(id, bid);
        return ResponseEntity.status(200).body(new ApiResponse("The Bid has been updated successfully."));
    }

    @DeleteMapping("/cancel/{id}")
    public ResponseEntity CancelBid(@PathVariable Integer id) {
        bidService.CancelOfBid(id);
        return ResponseEntity.status(200).body(new ApiResponse("The Bid has been Canceled ."));
    }

    @GetMapping("/check-expiration")
    public ResponseEntity checkExpiredBids() {
        bidService.checkAndCloseExpiredBids();
        return ResponseEntity.status(200).body(new ApiResponse("Expired bids have been closed."));
    }

    @GetMapping("/soon-expiring")
    public ResponseEntity getSoonExpiringBids() {
        return ResponseEntity.status(200).body(bidService.getBidsExpiringSoon());
    }


    @GetMapping("/close-auction/{auctionId}/{customerId}")
    public ResponseEntity closeAuctionAndSelectBid(@PathVariable Integer auctionId,@PathVariable Integer customerId) {
        bidService.closeAuctionAndSelectWinner(auctionId,customerId);
        return ResponseEntity.status(200).body(new ApiResponse("Auction closed and winning bid selected."));
    }
    @PostMapping("/biding/{auctionId}/{customerId}/{bidId}/{amount}")
    public ResponseEntity biding(@PathVariable Integer auctionId,@PathVariable Integer customerId,@PathVariable Integer bidId,@PathVariable Double amount){
        bidService.bidding(auctionId,customerId,bidId,amount);
        return ResponseEntity.status(200).body(new ApiResponse("Participates in the Bid."));
    }
}