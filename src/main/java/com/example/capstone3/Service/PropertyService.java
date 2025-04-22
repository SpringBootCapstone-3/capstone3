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
    public List<Property> getAllProperty(){
        return propertyRepository.findAll();
    }
    //ADD
    public void addProperty(Property property,Integer owner_id){
        //we can after check the ids
        Owner owner = ownerRepository.findOwnerById(owner_id);

        if(owner == null){
            throw new ApiException("Owner not found");
        }

        property.setOwner(owner);

        propertyRepository.save(property);
    }

    //Update
    public void updateProperty(Integer id,Property property){
        Property oldProperty = propertyRepository.findPropertyById(id);
        if(oldProperty == null){
            throw new ApiException("Property not found");
        }
        oldProperty.setDescription(property.getDescription());
        oldProperty.setTitle(property.getTitle());
        //oldProperty.setIsApproved();
        propertyRepository.save(oldProperty);
    }

    //DELETE
    public void deleteProperty(Integer id){
        Property delProperty = propertyRepository.findPropertyById(id);
        if(delProperty == null){
            throw new ApiException("Property not found");
        }
        propertyRepository.delete(delProperty);
    }

    //Add with

    //Assign



}