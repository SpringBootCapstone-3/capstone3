package com.example.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Pattern(regexp = "RENTAL|SALE")
    @Column(columnDefinition = "varchar(20) not null")
    private String contractType;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDate issueDate;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDate endDate;

    @Positive
    private Double totalAmount;

    @Column(columnDefinition = "varchar(20) not null")
    @Pattern(regexp = "ACTIVE|COMPLETED|CANCELLED")
    @NotEmpty(message = "status must be not empty")
    private String status;

}
