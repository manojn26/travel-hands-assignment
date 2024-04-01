package com.travelhandsbackend.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderDto {
    private Long id;

    private int quantity;

    private int pricePerUnit;

    private String type;

    private String stockName;

    private String status;
}
