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
@Table(name = "create_order")
public class CreateOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price_per_unit")
    private int pricePerUnit;

    @Column(name = "type")
    private String type;

    @Column(name = "stock_name")
    private String stockName;

    @Column(name = "status")
    private String status;
}
