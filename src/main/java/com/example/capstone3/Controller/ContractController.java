package com.example.capstone3.Controller;

import com.example.capstone3.Api.ApiResponse;
import com.example.capstone3.DTO.ContractDTO;
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
}