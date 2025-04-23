package com.example.capstone3.Service;


import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Admin;
import com.example.capstone3.Model.Auction;
import com.example.capstone3.Model.Property;
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



}