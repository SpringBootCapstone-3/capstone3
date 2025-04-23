package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.DTO.ContractDTO;
import com.example.capstone3.Model.Bid;
import com.example.capstone3.Model.Contract;
import com.example.capstone3.Model.Customer;
import com.example.capstone3.Repository.BidRepository;
import com.example.capstone3.Repository.ContractRepository;
import com.example.capstone3.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ContractService {

    private final ContractRepository contractRepository;
    private final BidRepository bidRepository;
    private final CustomerRepository customerRepository;
    private final EmailService emailService;

    public List<Contract> getAllContract() {
        return contractRepository.findAll();
    }


    public void addContract(ContractDTO contractDTO) {
        Customer customer = customerRepository.findCustomerById(contractDTO.getCustomer_id());
        Bid bid = bidRepository.findBidById(contractDTO.getBid_id());
        if (bid == null) {
            throw new ApiException("bid not found");
        }
        Contract contract = new Contract(null, contractDTO.getContractType(), contractDTO.getIssueDate(), contractDTO.getTotalAmount(), contractDTO.getStatus(), contractDTO.getNameOfNewOwner(), bid, customer);
        contractRepository.save(contract);
    }


    public void updateContract(ContractDTO contractDTO) {
        Contract contract = contractRepository.findContractById(contractDTO.getBid_id());
        if (contract == null) {
            throw new ApiException("Contract is not found");
        }
        contract.setContractType(contractDTO.getContractType());
        contract.setStatus(contractDTO.getStatus());
        contract.setIssueDate(contractDTO.getIssueDate());
        contract.setTotalAmount(contractDTO.getTotalAmount());
        contract.setNameOfNewOwner(contractDTO.getNameOfNewOwner());

        contractRepository.save(contract);
    }


    public void deleteContract(Integer id) {
        Contract contract = contractRepository.findContractById(id);

        if (contract == null) {
            throw new ApiException("Contract is not found");
        }
        contractRepository.delete(contract);
    }


//    public Contract generateContract(Integer bidId){
//        Bid bid = bidRepository.findBidById(bidId);
//        if(bid == null){
//            throw new ApiException("bid not found");
//        }
//        Set<Customer> customer = bid.getCustomers();
//        if(customer == null){
//            throw new ApiException("customer not found");
//        }
//        Contract contract= new Contract();
//        contract.setBid(bid);
//        contract.setContractType("SALE");
//        contract.setIssueDate(LocalDate.now());
//        contract.setTotalAmount(bid.getAmount());
//        contract.setStatus("ACTIVE");
//        contract.setNameOfNewOwner(contract.getNameOfNewOwner());
//
//        return contractRepository.save(contract);
//
//    }

    //2.endpoint Abdullah //Cancel
    public void cancelContract(Integer contractId) {
        Contract contract = contractRepository.findContractById(contractId);
        if (contract == null) {
            throw new ApiException("Contract not found");
        }

        if ("CANCELLED".equalsIgnoreCase(contract.getStatus())) {
            throw new ApiException("Contract is already cancelled");
        }

        Customer customer = contract.getCustomer();
        if (customer == null) {
            throw new ApiException("No customer associated with this contract");
        }

        // Send email
        String to = customer.getEmail();
        String subject = "Contract Cancellation Notice";
        String body = "Dear " + customer.getName() + ",\n\nYour contract #" + contract.getId() + " has been cancelled.\n\nRegards,\nReal Estate Team";

        emailService.sendEmail(to, subject, body);

        // Update status
        contract.setStatus("CANCELLED");
        contractRepository.save(contract);
    }

    //3.endpoint //Abdullah update Status

    public Contract updateContractStatus(Integer contractId, String status) {
        Contract contract = contractRepository.findContractById(contractId);
        if (contract == null) {
            throw new ApiException("Contract not found");
        }

        if (contract.getNameOfNewOwner() == null || contract.getNameOfNewOwner().isEmpty()) {
            throw new ApiException("Contract is missing nameOfNewOwner");
        }

        // Update status and issue date
        contract.setStatus(status);
        contract.setIssueDate(LocalDate.now());

        Contract updatedContract = contractRepository.save(contract);

        // Send email to the contract's customer
        Customer customer = contract.getCustomer();
        if (customer != null && customer.getEmail() != null) {
            String subject = "Contract Status Updated";
            String body = "Hello " + customer.getName() + ",\n\n"
                    + "Your contract #" + contract.getId() + " status has been updated to: " + status + ".\n"
                    + "Issue Date: " + contract.getIssueDate() + "\n\n"
                    + "Thank you.";
            emailService.sendEmail(customer.getEmail(), subject, body);
        }

        return updatedContract;
    }

    //5.Endpoint //Abdullah //Email
    public void sendContractDetailsToCustomer(String email) {
        Customer customer = customerRepository.findCustomerByEmail(email);
        if (customer == null) {
            throw new ApiException("Customer not found");
        }

        List<Contract> contracts = contractRepository.findAllByCustomer(customer);
        if (contracts.isEmpty()) {
            throw new ApiException("Customer has no contracts");
        }

        // Direct string concatenation
        String message = "";
        for (Contract contract : contracts) {
            message += "Contract ID: " + contract.getId() + "\n"
                    + "Type: " + contract.getContractType() + "\n"
                    + "Status: " + contract.getStatus() + "\n"
                    + "Total Amount: " + contract.getTotalAmount() + "\n"
                    + "Issue Date: " + contract.getIssueDate() + "\n"
                    + "Owner Name: " + contract.getNameOfNewOwner() + "\n\n";
        }

        // Send the email
        emailService.sendEmail(email, "Your Contract Info", message);

    }
}