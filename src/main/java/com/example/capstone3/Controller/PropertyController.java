package com.example.capstone3.Controller;

import com.example.capstone3.Api.ApiResponse;
import com.example.capstone3.Model.Property;
import com.example.capstone3.Service.PropertyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/property")
@RequiredArgsConstructor
public class PropertyController {
    private final PropertyService propertyService;

    //GET
    @GetMapping("/get")
    public ResponseEntity getAllProperty(){
        return ResponseEntity.status(200).body(propertyService.getAllProperty());
    }
    //add
    @PostMapping("/add/{idOwner}")
    public ResponseEntity addProperty(@PathVariable Integer idOwner,@RequestBody @Valid Property property){
        propertyService.addProperty(property,idOwner);
        return ResponseEntity.status(200).body(new ApiResponse("Added"));
    }

    //update
    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Integer id,@RequestBody @Valid Property property){
        propertyService.updateProperty(id, property);
        return ResponseEntity.status(200).body(new ApiResponse("Updated"));
    }
    //delete
    @DeleteMapping("/del/{id}")
    public ResponseEntity delProperty(@PathVariable Integer id){
        propertyService.deleteProperty(id);
        return ResponseEntity.status(200).body(new ApiResponse("Deleted"));
    }
}