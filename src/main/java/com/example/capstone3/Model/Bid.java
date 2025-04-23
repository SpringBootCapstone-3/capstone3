package com.example.capstone3.Model;

import com.example.capstone3.DTO.AuctionDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Positive
    private Double amount;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private LocalDateTime bid_time;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expirationDate;

    @Pattern(regexp = "open|closed|cancelled|")
    private String status;

    // علاقه مع الكستمر
    @ManyToMany(mappedBy = "bids")
    @JsonIgnore
    private Set<Customer> customers;

    // علاقه مع المزاد
    @ManyToOne
    @JoinColumn(name = "auction_id")
    @JsonIgnore
    private Auction auction;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "bid")
    @PrimaryKeyJoinColumn
    private Contract contract;
}