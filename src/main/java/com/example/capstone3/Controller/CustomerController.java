package com.example.capstone3.Controller;

import com.example.capstone3.Api.ApiResponse;
import com.example.capstone3.Model.Customer;
import com.example.capstone3.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/get")
    public ResponseEntity getAllCustomer(){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getAllCustomer());
    }

    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody Customer customer){
        customerService.addCustomer(customer);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("customer is added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCustomer(@PathVariable Integer id,@Valid @RequestBody Customer customer){
        customerService.updateCustomer(id, customer);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("customer is updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Integer id){
        customerService.deleteCustomer(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("customer is deleted"));
    }
}