package com.example.capstone3.Controller;


import com.example.capstone3.Api.ApiResponse;
import com.example.capstone3.Model.Admin;
import com.example.capstone3.Repository.AdminRepository;
import com.example.capstone3.Repository.OwnerRepository;
import com.example.capstone3.Service.AdminService;
import com.example.capstone3.Service.OwnerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final OwnerService ownerService;


    //     Get  Admin
    @GetMapping("/get")
    public ResponseEntity<?> getAllAdmin() {
        return ResponseEntity.ok(adminService.getAdmin());
    }

    //    Add new Admin
    @PostMapping("/add")
    public ResponseEntity<?> addAdmin(@RequestBody @Valid Admin admin) {
        adminService.addAdmin(admin);
        return ResponseEntity.ok(new ApiResponse("Added Admin!!"));
    }

    //     Update Admin
    @PutMapping("/update")
    public ResponseEntity<?> updateAdmin(@Valid @RequestBody Admin admin) {
        adminService.updateAdmin(admin);
        return ResponseEntity.ok(new ApiResponse("Update Admin!!"));
    }

    //     Delete Admin
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable Integer id) {
        adminService.deleteAdmin(id);
        return ResponseEntity.ok(new ApiResponse("Deleted Admin!!"));
    }

    // ( Endpoint 1 of Admin )list of properties that have not been approved (isApproved = false)
    @GetMapping("/unapproved-properties")
    public ResponseEntity<?> getUnapprovedProperties() {
        return ResponseEntity.ok(adminService.getUnapprovedProperties());
    }

    // ( Endpoint 2 of Admin ) Show all active auctions related to this owner's properties.
    @GetMapping("/admin/active-auctions-without-bids")
    public ResponseEntity<?> getAuctionsWithoutBids() {
        return ResponseEntity.ok(adminService.getActiveAuctionsWithoutBids());
    }

    // ( Endpoint 3 of Admin ) The admin blocked the owner(Blacklist).
    @PutMapping("/disable-owner/{ownerId}")
    public ResponseEntity<?> disableOwner(@PathVariable Integer ownerId) {
        ownerService.disableOwner(ownerId);
        return ResponseEntity.ok(new ApiResponse("Owner has been disabled (Blacklist) successfully."));
    }


    // ( Endpoint 4 of Admin ) Admin can end auction early for any reason
    @PutMapping("/end-auction-early/{auctionId}")
    public ResponseEntity<?> endAuctionEarly(@PathVariable Integer auctionId) {
        adminService.endAuctionEarly(auctionId);
        return ResponseEntity.ok(new ApiResponse("Auction has been ended early by admin."));
    }

    // ( Endpoint 5 of Admin ) Auction Statistics (Ended/Active)
    @GetMapping("/admin/auction-stats")
    public ResponseEntity<?> getAuctionStatsSimple() {
        return ResponseEntity.ok(new ApiResponse(adminService.getAuctionStats()));
    }

    //     ( Endpoint 6 of Admin ) send EmailWelcomeToOwner
    @PostMapping("/owners/send-welcome-emails")
    public ResponseEntity<?> sendWelcomeEmailsToAllOwners() {
        ownerService.sendWelcomeEmailsToAllOwners();
        return ResponseEntity.ok(new ApiResponse("Welcome emails sent to all owners."));
    }


    // ( Endpoint 7 of Admin ) The admin unblocked the owner (Remove from Blacklist).
    @PutMapping("/enable-owner/{ownerId}")
    public ResponseEntity<?> enableOwner(@PathVariable Integer ownerId) {
        ownerService.enableOwner(ownerId);
        return ResponseEntity.ok(new ApiResponse("Owner has been enabled."));
    }


}