package com.travelhandsbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TradeDetailsDto {
    private long id;

    private String tradeDataTime;

    private String stockName;

    private Long listingPrice;

    private int quantity;

    private String type;

    private int pricePerUnit;
}
