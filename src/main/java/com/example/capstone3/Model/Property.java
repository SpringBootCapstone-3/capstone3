package com.example.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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
    @Size(min = 3,message = "Can not be less than 3")
    @Column(columnDefinition = "varchar(30) not null")
    private String title;
    @NotEmpty(message = "Can not be empty")
    @Size(min = 15,message = "The description must be more than 15 digits ")
    @Column(columnDefinition = "varchar(200) not null")
    private String description;
    private Boolean isApproved = false;
    private Boolean isRented = false;



    @ManyToOne
    @JoinColumn(name = "customer_id" ,referencedColumnName = "id")
    @JsonIgnore
    private Owner owner;



    @OneToOne(cascade = CascadeType.ALL,mappedBy = "property")
    @PrimaryKeyJoinColumn
    private Auction auction;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "rental")
    @PrimaryKeyJoinColumn
    private Rental rental;


}
