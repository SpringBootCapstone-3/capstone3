
package com.example.capstone3.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ContractDTO {

    private Integer bid_id;
    private Integer customer_id;
    @NotEmpty(message = "contract can not be empty")
    private String contractType;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDate issueDate;


    @Positive
    private Double totalAmount;


    @Pattern(regexp = "ACTIVE|COMPLETED|CANCELLED")
    @NotEmpty(message = "status must be not empty")
    private String status;


    @NotEmpty(message = "name must be not empty")
    private String nameOfNewOwner;



}