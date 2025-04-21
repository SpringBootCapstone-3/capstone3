package com.example.capstone3.Repository;

import com.example.capstone3.Model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental,Integer> {
    Rental findRentalById(Integer id);
}
