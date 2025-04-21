package com.example.capstone3.Service;

import com.example.capstone3.Model.Property;
import com.example.capstone3.Repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyService {
    private final PropertyRepository propertyRepository;

    //GET
    public List<Property> getAllProperty(){
        return propertyRepository.findAll();
    }
    //ADD
    public void addProperty(Property property){
        propertyRepository.save(property);
    }
}
