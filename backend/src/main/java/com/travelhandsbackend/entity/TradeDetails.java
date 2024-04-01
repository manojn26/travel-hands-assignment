package com.travelhandsbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trade_details")
public class TradeDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "trade_data_time")
    private String tradeDataTime;

    @Column(name = "stock_name")
    private String stockName;

    @Column(name = "listing_price")
    private Long listingPrice;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "type")
    private String type;

    @Column(name = "price_per_unit")
    private int pricePerUnit;

}
