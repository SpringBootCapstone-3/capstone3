package com.example.capstone3.Repository;

import com.example.capstone3.Model.Contract;
import com.example.capstone3.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Integer> {

    Contract findContractById(Integer id);

    // Abdullah Ali1
    @Query("SELECT c FROM Contract c WHERE c.customer.id = :customerId ORDER BY c.id DESC")
    Contract findFirstByCustomerIdOrderByIdDesc(@Param("customerId") Integer customerId);
    String findContractByStatus(String status);
    List<Contract> findAllByCustomer(Customer customer);
    // --

}
