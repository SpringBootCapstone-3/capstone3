package com.example.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Can not be empty")
    @Size(min = 3, message = "Can not be less than 3")
    @Column(columnDefinition = "varchar(30) not null")
    private String title;

    @NotEmpty(message = "Can not be empty")
    @Size(min = 100, message = "The description must be more than 100 digits ")
    @Column(columnDefinition = "varchar(200) not null")
    private String description;

    @AssertFalse
    private Boolean isApproved = false;

//    private Boolean isRented = false;

    @Positive(message = "Price must be positive")
    @Column(nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @JsonIgnore
    private Owner owner;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "property")
    @PrimaryKeyJoinColumn
    private Auction auction;

}