package com.example.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime startTime;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime endTime;

    @Column(columnDefinition = "boolean not null")
    private Boolean isActive;

    @Column(columnDefinition = "int not null")
    private Double startingBid;

    @Column(columnDefinition = "varchar(20) not null")
    private String status;

    @OneToOne
    @JsonIgnore
    @MapsId
    private Property property;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "auction")
    private Set<Bid>bids;
}