package com.example.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Double amount;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date bid_time;
    // علاقه مع الكستمر
    @ManyToMany(mappedBy = "bids")
    private Set<Customer> customers;
    // علاقه مع المزاد
    @ManyToOne
    @JoinColumn(name = "auction_id")
    @JsonIgnore
    private Auction auction;
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "bid")
    @PrimaryKeyJoinColumn
    private Contract contract;
}