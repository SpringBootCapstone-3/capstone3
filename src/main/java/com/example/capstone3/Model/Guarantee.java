package com.example.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Guarantee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "Can not be null")
    @Min(value = 1200,message = "Can not be less than 1200 SAR")
    @Column(columnDefinition = "double not null")
    private double amount;
    private Boolean status = false;
    @Column(columnDefinition = "int not null")
    private Integer customer_id;
    @Column(columnDefinition = "int not null")
    private Integer rental_id;

    @ManyToOne
    @JoinColumn(name = "customer" , referencedColumnName = "id")
    @JsonIgnore
    private Customer customer;

    @OneToOne
    @MapsId
    @JsonIgnore
    private Rental rental;
}
