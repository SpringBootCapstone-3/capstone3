package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Bid;
import com.example.capstone3.Model.Customer;
import com.example.capstone3.Repository.BidRepository;
import com.example.capstone3.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final EmailService emailService;
    private final BidRepository bidRepository;

    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }


    public void addCustomer(Customer customer) {
        customerRepository.save(customer);
        String to = customer.getEmail();
        String subject = "Welcome to Our Service!";
        String body = "Dear " + customer.getName() + ",\n\nWelcome to our platform! We are excited to have you onboard.";

        // Assuming emailService is already injected and configured
        emailService.sendEmail(to, subject, body);
    }

    public void updateCustomer(Integer id, Customer customer) {
        Customer oldCustomer = customerRepository.findCustomerById(id);
        if (customer == null) {
            throw new ApiException("customer is not found");
        }
        oldCustomer.setName(customer.getName());
        oldCustomer.setAge(customer.getAge());
        oldCustomer.setEmail(customer.getEmail());
        oldCustomer.setPassword(customer.getPassword());

        customerRepository.save(oldCustomer);
    }


    public void deleteCustomer(Integer id) {
        Customer customer = customerRepository.findCustomerById(id);

        if (customer == null) {
            throw new ApiException("customer is not found");
        }
        customerRepository.delete(customer);
    }

    public Customer getCustomerById(Integer id) {
        return customerRepository.findCustomerById(id);
    }

    public void withdrawBid(Integer customerId, Integer bidId) {
        Bid bid = bidRepository.findBidById(bidId);

        if (!bid.getCustomers().contains(customerId)) {
            throw new RuntimeException("This bid does not belong to the customer.");
        }

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime endTime = bid.getAuction().getEndTime();

        if (endTime != null && now.isAfter(endTime)) {
            throw new RuntimeException("Auction has already ended, cannot withdraw bid.");
        }
        bidRepository.delete(bid);
    }


}