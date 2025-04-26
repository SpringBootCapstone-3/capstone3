package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Owner;
import com.example.capstone3.Repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerService {
    private final OwnerRepository ownerRepository;
    private final EmailService emailService;


    // Get All Owners
    public List<Owner> getOwners() {
        return ownerRepository.findAll();
    }

    //     Add new Admin
    public void addOwner(Owner owner) {
        ownerRepository.save(owner);
    }

    //     Update Admin
    public void updateOwner(Owner owner, Integer id) {
        Owner oldOwner = ownerRepository.findOwnerById(id);
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

        owner.setIsBanned(true);
        ownerRepository.save(owner);
    }

    //     ( Endpoint 6 of Admin ) send EmailWelcomeToOwner
    public void sendWelcomeEmailsToAllOwners() {
        List<Owner> owners = ownerRepository.findAll();

        for (Owner owner : owners) {
            sendWelcomeEmail(owner);
        }
    }

    private void sendWelcomeEmail(Owner owner) {
        String to = owner.getEmail();
        String subject = "Welcome to Our Service!";
        String body = "Dear " + owner.getName() + ",\n\n"
                + "Welcome to our service! We are happy to have you with us.\n\n"
                + "Please read these important rules:\n"
                + "1. The admin has the right to cancel any bid at any time.\n"
                + "2. All property listings need admin approval.\n"
                + "3. If you break the rules, your account may be blocked.\n"
                + "4. Please make sure all the information in your listings is correct and true.\n\n"
                + "By using our platform, you agree to follow these rules.\n\n"
                + "Thank you for joining us!\n"
                + "The Real Estate Platform Team";

        emailService.sendEmail(to, subject, body);
    }


    // ( Endpoint 7 of Admin ) The admin unblocked the owner (Remove from Blacklist).
    public void enableOwner(Integer ownerId) {
        Owner owner = ownerRepository.findOwnerById(ownerId);
        if (owner == null) {
            throw new ApiException("Owner Not Found!!");
        }

        owner.setIsBanned(false);
        ownerRepository.save(owner);
    }


}