package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Customer;
import com.example.capstone3.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final EmailService emailService;

    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }


//    public void addCustomer(Customer customer){
//        customerRepository.save(customer);
//    }

    public void addCustomer(Customer customer){
        customerRepository.save(customer);
        String to = customer.getEmail();
        String subject = "Welcome to Our Service!";
        String body = "Dear " + customer.getName() + ",\n\nWelcome to our platform! We are excited to have you onboard.";

        // Assuming emailService is already injected and configured
        emailService.sendEmail(to, subject, body);
    }
    public void updateCustomer(Integer id,Customer customer){
        Customer oldCustomer=customerRepository.findCustomerById(id);
        if(customer==null){
            throw new ApiException("customer is not found");
        }
        oldCustomer.setName(customer.getName());
        oldCustomer.setAge(customer.getAge());
        oldCustomer.setEmail(customer.getEmail());
        oldCustomer.setPassword(customer.getPassword());

        customerRepository.save(oldCustomer);
    }


    public void deleteCustomer(Integer id){
        Customer customer=customerRepository.findCustomerById(id);

        if(customer==null){
            throw new ApiException("customer is not found");
        }
        customerRepository.delete(customer);
    }

    public Customer getCustomerById(Integer id){
        return customerRepository.findCustomerById(id);
    }


}