package com.example.capstone3.Controller;

import com.example.capstone3.Api.ApiResponse;
import com.example.capstone3.Model.Owner;
import com.example.capstone3.Service.OwnerService;
import com.example.capstone3.Service.PropertyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/real_estate/owners")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;
    private final PropertyService propertyService;

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
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateOwner(@RequestBody @Valid Owner owner ,@PathVariable Integer id) {
        ownerService.updateOwner(owner ,id);
        return ResponseEntity.ok(new ApiResponse("Updated Owner!!"));
    }

    // Delete Owner
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOwner(@PathVariable Integer id) {
        ownerService.deleteOwner(id);
        return ResponseEntity.ok(new ApiResponse("Deleted Owner!!"));
    }

    // ( Endpoint 1 of Owner ) Show all active auctions related to this owner's properties.
    @GetMapping("/owners/{ownerId}/active-auctions")
    public ResponseEntity<?> getActiveAuctions(@PathVariable Integer ownerId) {
        return ResponseEntity.ok(propertyService.getActiveAuctionPropertiesForOwner(ownerId));
    }

    // ( Endpoint 2 of Owner ) Owner can edit only the property address (IsApproved = false)
    @PutMapping("/{ownerId}/property-title-update/{propertyId}")
    public ResponseEntity<?> updateTitle(@PathVariable Integer ownerId, @PathVariable Integer propertyId, @RequestParam  String newTitle) {
        propertyService.updatePropertyTitle(ownerId, propertyId, newTitle);
        return ResponseEntity.ok(new ApiResponse("Property title updated."));
    }
}