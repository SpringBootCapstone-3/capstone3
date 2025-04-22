package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.DTO.ContractDTO;
import com.example.capstone3.Model.Bid;
import com.example.capstone3.Model.Contract;
import com.example.capstone3.Repository.BidRepository;
import com.example.capstone3.Repository.ContractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContractService {

    private final ContractRepository contractRepository;
    private final BidRepository bidRepository;

    public List<Contract> getAllContract(){
        return contractRepository.findAll();
    }


    public void addContract(ContractDTO contractDTO){
        Bid bid=bidRepository.findBidById(contractDTO.getBid_id());
        if(bid==null){
            throw new ApiException("bid not found");
        }
        Contract contract=new Contract(null, contractDTO.getContractType(),contractDTO.getIssueDate(),contractDTO.getTotalAmount(), contractDTO.getStatus(), contractDTO.getNameOfNewOwner(),bid);
        contractRepository.save(contract);
    }


    public void updateContract(ContractDTO contractDTO){
        Contract contract=contractRepository.findContractById(contractDTO.getBid_id());
        if(contract==null){
            throw new ApiException("Contract is not found");
        }
        contract.setContractType(contractDTO.getContractType());
        contract.setStatus(contractDTO.getStatus());
        contract.setIssueDate(contractDTO.getIssueDate());
        contract.setTotalAmount(contractDTO.getTotalAmount());
        contract.setNameOfNewOwner(contractDTO.getNameOfNewOwner());

        contractRepository.save(contract);
    }


    public void deleteContract(Integer id){
        Contract contract=contractRepository.findContractById(id);

        if(contract==null){
            throw new ApiException("Contract is not found");
        }
        contractRepository.delete(contract);
    }
}