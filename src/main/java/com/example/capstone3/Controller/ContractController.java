package com.example.capstone3.Controller;

import com.example.capstone3.Api.ApiResponse;
import com.example.capstone3.DTO.ContractDTO;
import com.example.capstone3.Model.Contract;
import com.example.capstone3.Service.ContractService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/contract")
@RequiredArgsConstructor
public class ContractController {


    private final ContractService contractService;


    @GetMapping("/get")
    public ResponseEntity getAllContract(){
        return ResponseEntity.status(HttpStatus.OK).body(contractService.getAllContract());
    }

    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody ContractDTO contractDTO){
        contractService.addContract(contractDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Contract is added"));
    }

    @PutMapping("/update")
    public ResponseEntity updateContract(@Valid @RequestBody ContractDTO contractDTO){
        contractService.updateContract(contractDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Contract is updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteContract(@PathVariable Integer id){
        contractService.deleteContract(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Contract is deleted"));
    }

//    //1.endpoint Abdullah
//    @PostMapping("/generate/{bidId}")
//    public ResponseEntity generateContract(@PathVariable Integer bidId) {
//        Contract contract = contractService.generateContract(bidId);
//        return ResponseEntity.status(200).body(contract);
//    }

    //2.endpoint Abdullah //Cancel
    @PutMapping("/contract/{contractId}/cancel")
    public ResponseEntity<ApiResponse> cancelContract(@PathVariable Integer contractId) {
        contractService.cancelContract(contractId);
        return ResponseEntity.status(200).body(new ApiResponse("Contract has been cancelled successfully."));
    }

    //3.update status Abdullah
    @PutMapping("/update-status/{contractId}")
    public ResponseEntity updateContractStatus(@PathVariable Integer contractId, @RequestParam String status) {  // Use @Valid to trigger validation
        // Call service to update contract status
        Contract updatedContract = contractService.updateContractStatus(contractId, status);
        return ResponseEntity.status(200).body(updatedContract);
    }

    //5 Abdullah
    @PostMapping("/send-info")
    public ResponseEntity<String> sendContractInfoToCustomer(@RequestParam String email) {
        contractService.sendContractDetailsToCustomer(email);
        return ResponseEntity.ok("Contract info has been sent to the customer.");
    }

}