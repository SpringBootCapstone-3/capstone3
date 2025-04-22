package com.example.capstone3.Controller;

import com.example.capstone3.Api.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/rental")
public class RentalController {
    private final RentalService rentalService;
    @GetMapping("/get")
    public ResponseEntity getAllRental(){
        return ResponseEntity.status(200).body(rentalService.getAllRental());
    }
    @PostMapping("/add")
    public ResponseEntity addRental(@RequestBody @Valid Rental rental){
        rentalService.addRental(rental);
        return ResponseEntity.status(200).body(new ApiResponse("he Bid has been added successfully."));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateRental(@PathVariable Integer id,@RequestBody @Valid Rental rental){
        rentalService.updateRental(id,rental);
        return ResponseEntity.status(200).body(new ApiResponse("he Bid has been updated successfully."));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteRental(@PathVariable Integer id){
        rentalService.deleteRental(id);
        return ResponseEntity.status(200).body(new ApiResponse("he Bid has been deleted successfully."));

    }


}
