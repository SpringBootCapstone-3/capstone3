package com.example.capstone3.Repository;

import com.example.capstone3.Model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Integer> {
    Property findPropertyById(Integer id);

    List<Property> findByIsApprovedFalse();

    @Query("select p from Property p where p.owner.id = ?1 and p.auction is not null and p.auction.isActive = true")
    List<Property> findActiveAuctionsByOwnerId(Integer ownerId);

    List<Property> findByOwnerIdAndIsRentedFalse(Integer ownerId);

    @Query("select p from Property p where p.title=?1")
    List<Property> findPropertyByTitle(String title);
}
