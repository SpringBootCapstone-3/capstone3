package com.example.capstone3.Service;


import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Admin;
import com.example.capstone3.Repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    final AdminRepository adminRepository;

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


}
