package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.DTO.RentalDTO;
import com.example.capstone3.Model.Customer;
import com.example.capstone3.Model.Guarantee;
import com.example.capstone3.Model.Rental;
import com.example.capstone3.Repository.CustomerRepository;
import com.example.capstone3.Repository.GuaranteeRepository;
import com.example.capstone3.Repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GuaranteeService {
    private final GuaranteeRepository guaranteeRepository;
    private final CustomerRepository customerRepository;
    private final RentalRepository rentalRepository;

    //GET
    public List<Guarantee> getAllGuarantees(){
        return guaranteeRepository.findAll();
    }

    //ADD
//    public void addGuarantee(RentalDTO rentalDTO){
//        //Don't forget to get customer id and rental id please.
//      //  Customer customer = customerRepository.findCustomerById(rentalDTO.getCustomer_id());
//        if(customer == null){
//            throw new ApiException("not found customer");
//        }
        //Guarantee guarantee = new Guarantee(null, rentalDTO.getGuarantee_id(), rentalDTO.getIsActive(), rentalDTO.getCustomer_id(), )
       // guaranteeRepository.save(guarantee);
   // }

    //update
    public void updateGuarantee(Integer id,Guarantee guarantee){
        Guarantee oldGuarantee = guaranteeRepository.findGuaranteeById(id);
        if(oldGuarantee == null){
            throw new ApiException("Guarantee not found");
        }
        oldGuarantee.setAmount(guarantee.getId());
        guaranteeRepository.save(oldGuarantee);
    }
    //delete
    public void deleteGuarantee(Integer id){
        Guarantee delGuarantee = guaranteeRepository.findGuaranteeById(id);
        if (delGuarantee == null){
            throw new ApiException("Guarantee not found");
        }
        guaranteeRepository.delete(delGuarantee);
    }

    //Add guarantee with customer and rental
   // public void addWithCustomerAndRental(Guarantee guarantee,Integer guaranteeId ,Integer customerId, Integer rentalId){
        //Guarantee guarantee = guaranteeRepository.findGuaranteeById(Integer guaranteeId);
        //Customer customer = customerRepository.findCustomerById(customerId);
        //Rental rental = rentalRepository.findRentalById(rentalId);
    //if(guarantee == null){
    //throw new ApiException("guarantee not found");
    //}
        //if(customer == null){
        //throw new ApiException("Customer not found");
        //}
        //if(rental == null){
        //throw new ApiException("rental not found");
        //}
        //guarantee.setCustomer(customer);
    //guarantee.setRental(rental);
        //guaranteeRepository.save(guarantee);

    //}


    //Assign Guarantee to Customer
    // public void assignGuaranteeToCustomer(Integer guaranteeId ,Integer customerId){
    //Guarantee guarantee = guaranteeRepository.findGuaranteeById(Integer guaranteeId);
    //Customer customer = customerRepository.findCustomerById(customerId);
    //if(guarantee == null){
    //throw new ApiException("guarantee not found");
    //}
    //if(customer == null){
    //throw new ApiException("Customer not found");
    //}
    //guarantee.setCustomer(customer);
    //guaranteeRepository.save(guarantee);

    //}
}
