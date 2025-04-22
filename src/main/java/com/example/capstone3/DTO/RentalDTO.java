package com.example.capstone3.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
@Data
@AllArgsConstructor
public class RentalDTO {
    private Integer guarantee_id;
    private Integer customer_id;
    private Date start_date;
    private Date end_date;
    private Double month_price;
    private Boolean isActive;
}
