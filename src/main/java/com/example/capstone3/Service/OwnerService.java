package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Owner;
import com.example.capstone3.Model.Property;
import com.example.capstone3.Repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerService {
    private final OwnerRepository ownerRepository;

    // Get All Owners
    public List<Owner> getOwners() {
        return ownerRepository.findAll();
    }

    //     Add new Admin
    public void addOwner(Owner owner) {
        ownerRepository.save(owner);
    }

    //     Update Admin
    public void updateOwner(Owner owner) {
        Owner oldOwner = ownerRepository.findOwnerById(owner.getId());
        Owner ownerByEmail = ownerRepository.findOwnerByEmail(owner.getEmail());
        Owner ownerByPhone = ownerRepository.findOwnerByPhoneNumber(owner.getPhoneNumber());

        if (oldOwner == null) {
            throw new ApiException("Owner Not Found!!");
        }
        oldOwner.setName(owner.getName());
        if (ownerByEmail != null && !ownerByEmail.getId().equals(owner.getId())) {
            throw new ApiException("Email is already taken!");
        }
        oldOwner.setEmail(owner.getEmail());
        if (ownerByPhone != null && !ownerByPhone.getId().equals(owner.getId())) {
            throw new ApiException("Phone number is already used!");
        }
        oldOwner.setPhoneNumber(owner.getPhoneNumber());

        ownerRepository.save(oldOwner);
    }

    //    Delete Owner
    public void deleteOwner(Integer id) {
        Owner owner = ownerRepository.findOwnerById(id);
        if (owner == null) {
            throw new ApiException("Owner Not Found!!");
        }
        ownerRepository.delete(owner);
    }

    // ( Endpoint 3 of Admin ) The admin blocked the owner(Blacklist).
    public void disableOwner(Integer ownerId) {
        Owner owner = ownerRepository.findOwnerById(ownerId);
        if (owner == null) {
            throw new ApiException("Owner Not Found!!");
        }
        owner.setEmail("blocked-" + owner.getName() + ".id." + owner.getId() + "@system.local");
        ownerRepository.save(owner);
    }



}