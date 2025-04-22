package com.example.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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


    @NotEmpty(message = "contract can not be empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String contractType;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDate issueDate;


    @Positive
    private Double totalAmount;

    @Column(columnDefinition = "varchar(20) not null")
    @Pattern(regexp = "ACTIVE|COMPLETED|CANCELLED")
    @NotEmpty(message = "status must be not empty")
    private String status;

    @Column(columnDefinition = "varchar(20) not null")
    @NotEmpty(message = "name must be not empty")
    private String nameOfNewOwner;
    @OneToOne
    @MapsId
    @JsonIgnore
    private Bid bid;
}