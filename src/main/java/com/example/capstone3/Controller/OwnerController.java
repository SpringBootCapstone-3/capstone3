package com.example.capstone3.Controller;

import com.example.capstone3.Api.ApiResponse;
import com.example.capstone3.Model.Owner;
import com.example.capstone3.Service.OwnerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/real_estate/owner")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;

    // Get All Owners
    @GetMapping("/get")
    public ResponseEntity<?> getAllOwners() {
        return ResponseEntity.ok(ownerService.getOwners());
    }

    // Add Owner
    @PostMapping("/add")
    public ResponseEntity<?> addOwner(@RequestBody @Valid Owner owner) {
        ownerService.addOwner(owner);
        return ResponseEntity.ok(new ApiResponse("Added Owner!!"));
    }

    // Update Owner
    @PutMapping("/update")
    public ResponseEntity<?> updateOwner(@RequestBody @Valid Owner owner) {
        ownerService.updateOwner(owner);
        return ResponseEntity.ok(new ApiResponse("Updated Owner!!"));
    }

    // Delete Owner
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOwner(@PathVariable Integer id) {
        ownerService.deleteOwner(id);
        return ResponseEntity.ok(new ApiResponse("Deleted Owner!!"));
    }
}