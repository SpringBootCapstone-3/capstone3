package com.example.capstone3.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@Entity
@NoArgsConstructor
@Setter
@Getter
public class Admin {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;


    @NotEmpty(message = "Username must not be empty")
    @Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters")
    @Column(columnDefinition = "varchar(30) not null unique")
    private String username;

    @NotEmpty(message = "Password must not be empty")
    @Size(min = 6, message = "Password must be at least 6 characters")
    @Column(columnDefinition = "varchar(100) not null")
    private String password;



}