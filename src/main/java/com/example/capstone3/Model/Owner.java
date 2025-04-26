package com.example.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotEmpty(message = "Name must not be empty")
    @Size(min = 3, max = 50, message = "Name length must be between 3 and 50 characters")
    @Column(columnDefinition = "varchar(50) not null")
    private String name;

    @NotEmpty(message = "Email must not be empty")
    @Email(message = "Email should be valid")
    @Column(columnDefinition = "varchar(100) not null unique")
    private String email;

    @NotBlank(message = "Phone number must not be empty")
    @Pattern(regexp = "^05\\d{8}$", message = "Phone number must start with 05 and be 10 digits")
    @Column(columnDefinition = "varchar(10) not null unique")
    private String phoneNumber;

    @NotEmpty(message = "Password must not be empty")
    @Size(min = 6, message = "Password must be at least 6 characters")
    @Column(columnDefinition = "varchar(100) not null")
    private String password;

    @OneToMany(mappedBy = "owner")
    private Set<Property> properties;

    private Boolean isBanned = false;

}