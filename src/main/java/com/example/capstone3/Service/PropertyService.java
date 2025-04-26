package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.*;
import com.example.capstone3.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PropertyService {
    private final PropertyRepository propertyRepository;
    private final OwnerRepository ownerRepository;
    private final CustomerRepository customerRepository;
    private final BidRepository bidRepository;
    private final AuctionRepository auctionRepository;


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

        if (owner.getIsBanned()) {
            throw new ApiException("Banned owners are not allowed to add properties");
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

    //4.endpoint //Abdullah //range
    public List<Property> getPropertyByPriceRange(Double min, Double max) {
        List<Property> allProperty = propertyRepository.findAll();
        List<Property> filtered = new ArrayList<>();
        for (Property property : allProperty) {
            if (property.getPrice() >= min && property.getPrice() <= max) {
                filtered.add(property);
            }
        }
        return filtered;
    }

    //Assign
    public void cancelProperty(Integer propertyId, Integer ownerId) {
        Property property = propertyRepository.findPropertyById(propertyId);
        Owner owner = ownerRepository.findOwnerById(ownerId);
        if (owner == null) {
            throw new ApiException("Owner Not Found");
        }
        if (property == null) {
            throw new ApiException("Property Not Found");
        }
        propertyRepository.delete(property);
    }

    // عرض العقار لهذا المالك
    public Set<Property> getProperties(Integer customerId, Integer ownerId) {
        Customer customer = customerRepository.findCustomerById(customerId);
        Owner owner = ownerRepository.findOwnerById(ownerId);
        if (customer == null) {
            throw new ApiException("Customer Not Found");
        }
        if (owner == null) {
            throw new ApiException("Owner Not Found");
        }
        return owner.getProperties();
    }

    public void changeApproved(Integer ownerId, Integer propertyId) {
        Owner owner = ownerRepository.findOwnerById(ownerId);
        Property property = propertyRepository.findPropertyById(propertyId);
        if (owner == null) {
            throw new ApiException("owner Not Found");
        }
        if (property == null) {
            throw new ApiException("Property Not Found");
        }
        property.setIsApproved(true);
        propertyRepository.save(property);
    }

    public Auction checkAuctionOfProperty(Integer propertyId) {
        Property property = propertyRepository.findPropertyById(propertyId);
        Auction auction = auctionRepository.findAuctionsById(property.getAuction().getId());
        if (property == null) {
            throw new ApiException("Property Not Found");
        }
        if (auction == null) {
            throw new ApiException("auction Not Found");
        }
        return auction;
    }

    public List<Property> classificationProperty(String title) {
        List<Property> properties = propertyRepository.findPropertyByTitle(title);
        List<Property> propertyList = new ArrayList<>();
        if (title == null) {
            throw new ApiException("title Not Found");
        }
        for (Property p : properties) {
            if (p.getTitle().equalsIgnoreCase(title)) {
                propertyList.add(p);
            }
        }
        return propertyList;
    }

}