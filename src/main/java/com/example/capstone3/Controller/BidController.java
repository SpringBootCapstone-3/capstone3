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
    public ResponseEntity getAllBid(){
        return ResponseEntity.status(200).body(bidService.getBid());
    }
    @PostMapping("/add")
    public ResponseEntity addBid(@RequestBody @Valid Bid bid){
        bidService.addBid(bid);
        return ResponseEntity.status(200).body(new ApiResponse("The Bid has been added successfully."));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateBid(@PathVariable Integer id,@RequestBody @Valid Bid bid){
        bidService.updateBid(id,bid);
        return ResponseEntity.status(200).body(new ApiResponse("The Bid has been updated successfully."));
    }
    @DeleteMapping("/delete")
    public ResponseEntity deleteBid(@PathVariable Integer id){
        bidService.deleteBid(id);
        return ResponseEntity.status(200).body(new ApiResponse("The Bid has been deleted successfully."));
    }
}
