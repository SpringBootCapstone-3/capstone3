package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Admin;
import com.example.capstone3.Model.Owner;
import com.example.capstone3.Model.Property;
import com.example.capstone3.Repository.AdminRepository;
import com.example.capstone3.Repository.OwnerRepository;
import com.example.capstone3.Repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyService {
    private final PropertyRepository propertyRepository;
    private final OwnerRepository ownerRepository;
    private final AdminRepository adminRepository;


    //GET
    public List<Property> getAllProperty() {
        return propertyRepository.findAll();
    }

    //ADD
    public void addProperty(Property property, Integer owner_id) {
        //we can after check the ids
        Owner owner = ownerRepository.findOwnerById(owner_id);

        if (owner == null) {
            throw new ApiException("Owner not found");
        }

        property.setOwner(owner);

        propertyRepository.save(property);
    }

    // Update
    public void updateProperty(Integer id, Property property) {
        Property oldProperty = propertyRepository.findPropertyById(id);
        if (oldProperty == null) {
            throw new ApiException("Property not found");
        }
        oldProperty.setDescription(property.getDescription());
        oldProperty.setTitle(property.getTitle());
        //oldProperty.setIsApproved();
        propertyRepository.save(oldProperty);
    }

    //DELETE
    public void deleteProperty(Integer id) {
        Property delProperty = propertyRepository.findPropertyById(id);
        if (delProperty == null) {
            throw new ApiException("Property not found");
        }
        propertyRepository.delete(delProperty);
    }

    // ( Endpoint 1 of Owner ) Show all active auctions related to this owner's properties.
    public List<Property> getActiveAuctionPropertiesForOwner(Integer ownerId) {
        return propertyRepository.findActiveAuctionsByOwnerId(ownerId);
    }

    // ( Endpoint 2 of Owner ) Owner can edit only the property address (IsApproved = false)
    public void updatePropertyTitle(Integer ownerId, Integer propertyId, String newTitle) {
        Property property = propertyRepository.findPropertyById(propertyId);
        if (property == null) {
            throw new ApiException("Property not found");
        }
        if (!property.getOwner().getId().equals(ownerId)) {
            throw new RuntimeException("Property does not belong to this owner.");
        }
        if (property.getIsApproved()) {
            throw new RuntimeException("Property already approved. You can't change the title.");
        }

        property.setTitle(newTitle);
        propertyRepository.save(property);
    }

    //Add with

    //Assign


}