package com.example.capstone3.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental,Integer> {
    Rental findRentalById(Integer id);
}
