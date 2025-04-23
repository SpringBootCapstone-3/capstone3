package com.example.capstone3.Controller;

import com.example.capstone3.Api.ApiResponse;
import com.example.capstone3.Model.Property;
import com.example.capstone3.Service.PropertyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/property")
@RequiredArgsConstructor
public class PropertyController {
    private final PropertyService propertyService;

    //GET
    @GetMapping("/get")
    public ResponseEntity getAllProperty() {
        return ResponseEntity.status(200).body(propertyService.getAllProperty());
    }

    //add
    @PostMapping("/add/{idOwner}")
    public ResponseEntity addProperty(@PathVariable Integer idOwner, @RequestBody @Valid Property property) {
        propertyService.addProperty(property, idOwner);
        return ResponseEntity.status(200).body(new ApiResponse("Added"));
    }

    //update
    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody @Valid Property property) {
        propertyService.updateProperty(id, property);
        return ResponseEntity.status(200).body(new ApiResponse("Updated"));
    }

    //delete
    @DeleteMapping("/del/{id}")
    public ResponseEntity deleteProperty(@PathVariable Integer id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.status(200).body(new ApiResponse("Deleted"));
    }

    @GetMapping("/filter-by-price/{min}/{max}")
    public ResponseEntity<List<Property>> filterPropertiesByPrice(
            @PathVariable Double min,
            @PathVariable Double max) {

        List<Property> properties = propertyService.getPropertyByPriceRange(min, max);
        return ResponseEntity.ok(properties);
    }

    @GetMapping("/getPro/{customerId}/{ownerId}")
    public ResponseEntity getProperties(@PathVariable Integer customerId, @PathVariable Integer ownerId) {
        return ResponseEntity.status(200).body(propertyService.getProperties(customerId, ownerId));
    }

    @PutMapping("/change/{ownerid}/{propertyId}")
    public ResponseEntity changeApproved(@PathVariable Integer ownerid, @PathVariable Integer propertyId) {
        propertyService.changeApproved(ownerid, propertyId);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully"));
    }

    @DeleteMapping("/canselProperty/{propertyId}/{ownerId}")
    public ResponseEntity canselProperty(@PathVariable Integer propertyId, @PathVariable Integer ownerId) {
        propertyService.cancelProperty(propertyId, ownerId);
        return ResponseEntity.status(200).body(new ApiResponse("Deleted"));
    }

    @GetMapping("gitAuctionOfProperty/{propertyId}")
    public ResponseEntity checkAuctionOfProperty(@PathVariable Integer propertyId) {
        return ResponseEntity.status(200).body(propertyService.checkAuctionOfProperty(propertyId));
    }

    @GetMapping("/class")
    public ResponseEntity classificationProperty(@RequestBody @Valid String title) {
        return ResponseEntity.status(200).body(propertyService.classificationProperty(title));
    }


}