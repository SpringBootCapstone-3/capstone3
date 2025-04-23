package com.example.capstone3.Service;


import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.*;
import com.example.capstone3.Repository.AdminRepository;
import com.example.capstone3.Repository.AuctionRepository;
import com.example.capstone3.Repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final PropertyRepository propertyRepository;
    private final AuctionRepository auctionRepository;
    private final EmailService emailService;


    //    Get all  Admin
    public List<Admin> getAdmin() {
        return adminRepository.findAll();
    }

    //    Add new Admin
    public void addAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    //    Update Admin
    public void updateAdmin(Admin admin) {
        Admin oldAdmin = adminRepository.findAdminById(admin.getId());
        if (oldAdmin == null) {
            throw new ApiException("Admin Not Found!!");
        }
        oldAdmin.setUsername(admin.getUsername());
        oldAdmin.setPassword(admin.getPassword());

        adminRepository.save(oldAdmin);
    }

    //    Delete Admin
    public void deleteAdmin(Integer id) {
        Admin admin = adminRepository.findAdminById(id);
        if (admin == null) {
            throw new ApiException("Admin Not Found!!");
        }
        adminRepository.delete(admin);

    }

    // ( Endpoint 1 of Admin )list of properties that have not been approved (isApproved = false)
    public List<Property> getUnapprovedProperties() {
        return propertyRepository.findByIsApprovedFalse();
    }

    // ( Endpoint 2 of Admin ) Show all active auctions related to this owner's properties
    public List<Auction> getActiveAuctionsWithoutBids() {
        return auctionRepository.findActiveAuctionsWithoutBids();
    }

    // ( Endpoint 4 of Admin ) Admin can end auction early for any reason
    public void endAuctionEarly(Integer auctionId) {

        Auction auction = auctionRepository.findAuctionsById(auctionId);
        if (auction == null) {
            throw new ApiException("Auction is not found");
        }
        if (!auction.getIsActive()) {
            throw new RuntimeException("Auction is already ended.");
        }

        auction.setIsActive(false);
        auction.setEndTime(LocalDateTime.now());
        auctionRepository.save(auction);
    }

    // ( Endpoint 5 of Admin ) Auction Statistics (Ended/Active)
    public String getAuctionStats() {
        int active = auctionRepository.countActiveAuctions();
        int ended = auctionRepository.countEndedAuctions();

        return "Active auctions: " + active + " | Ended auctions: " + ended;
    }

    // ( Endpoint 6 of Admin ) send EmailWelcomeToOwner
//    public void sendEmailWelcomeToOwner(Owner owner) {
//        String to = owner.getEmail();
//        String subject = "Welcome to Our Service!";
//
//        String body = "Dear " + owner.getName() + ",\n\n"
//                + "Welcome to our real estate platform! We’re excited to have you as part of our network of trusted property owners.\n\n"
//                + "📌 Please note the following important terms of service:\n"
//                + "1. The platform reserves the right to cancel or void any bid at any time due to legal, technical, or administrative reasons.\n"
//                + "2. All property listings are subject to approval.\n"
//                + "3. Violations of platform policies may lead to suspension or deactivation of your account.\n"
//                + "4. Ensure that all information provided in listings is accurate and up to date.\n\n"
//                + "By using our platform, you agree to abide by these terms.\n\n"
//                + "Thank you for joining us!\n"
//                + "Real Estate Platform Team";
//
//        emailService.sendEmail(to, subject, body);
//    }



}