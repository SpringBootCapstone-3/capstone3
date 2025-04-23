package com.example.capstone3.Controller;

import com.example.capstone3.Api.ApiResponse;
import com.example.capstone3.DTO.AuctionDTO;
import com.example.capstone3.Service.AuctionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auction")
@RequiredArgsConstructor
public class AuctionController {

    private final AuctionService auctionService;


    @GetMapping("/get")
    public ResponseEntity getAllAuction(){
        return ResponseEntity.status(HttpStatus.OK).body(auctionService.getAllAuction());
    }

    @PostMapping("/add")
    public ResponseEntity addAuction(@Valid @RequestBody AuctionDTO auctionDTO){
        auctionService.addAuction(auctionDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Auction is add"));
    }

    @PutMapping("/update")
    public ResponseEntity updateAuction( @Valid @RequestBody AuctionDTO auctionDTO){
        auctionService.updateAuction(auctionDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Auction is updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteAuction(@PathVariable Integer id){
        auctionService.deleteAuction(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Auction is deleted"));
    }

    @GetMapping("/most-popular")
    public ResponseEntity getMostPopularAuctions() {
        return ResponseEntity.status(HttpStatus.OK).body(auctionService.getMostPopularAuctions());
    }
}