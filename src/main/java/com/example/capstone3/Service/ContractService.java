package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Contract;
import com.example.capstone3.Repository.ContractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContractService {

    private final ContractRepository contractRepository;

    public List<Contract> getAllContract(){
        return contractRepository.findAll();
    }


    public void addContract(Contract contract){
        contractRepository.save(contract);
    }


    public void updateContract(Integer id,Contract contract){
        Contract oldContract=contractRepository.findContractById(id);
        if(oldContract==null){
            throw new ApiException("Contract is not found");
        }
        oldContract.setContractType(contract.getContractType());
        oldContract.setStatus(contract.getStatus());
        oldContract.setStartDate(contract.getStartDate());
        oldContract.setEndDate(contract.getEndDate());
        oldContract.setIssueDate(contract.getIssueDate());
        oldContract.setTotalAmount(contract.getTotalAmount());

        contractRepository.save(oldContract);
    }


    public void deleteContract(Integer id){
        Contract contract=contractRepository.findContractById(id);

        if(contract==null){
            throw new ApiException("Contract is not found");
        }
        contractRepository.delete(contract);
    }
}
