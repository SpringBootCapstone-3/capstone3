package com.example.capstone3.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AuctionDTO {

    private Integer properaty_id;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime startTime;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime endTime;

    @NotNull(message = "isActive must be not null")
    private Boolean isActive;

    @Positive
    @NotNull
    private Double startingBid;

    @Pattern(regexp = "ACTIVE|CLOSED|SCHEDULED")
    private String status; // "ACTIVE", "CLOSED", "SCHEDULED"
}