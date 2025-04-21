package com.example.capstone3.Repository;

import com.example.capstone3.Model.Guarantee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuaranteeRepository extends JpaRepository<Guarantee,Integer> {
    Guarantee findGuaranteeById(Integer id);
}
