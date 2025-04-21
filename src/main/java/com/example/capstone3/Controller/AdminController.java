package com.example.capstone3.Controller;


import com.example.capstone3.Api.ApiResponse;
import com.example.capstone3.Model.Admin;
import com.example.capstone3.Repository.AdminRepository;
import com.example.capstone3.Service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/real_estate/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;


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
}
