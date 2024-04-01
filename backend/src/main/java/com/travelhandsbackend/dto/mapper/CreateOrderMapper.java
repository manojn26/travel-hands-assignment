package com.travelhandsbackend.dto.mapper;

import com.travelhandsbackend.dto.CreateOrderDto;
import com.travelhandsbackend.entity.CreateOrder;

public class CreateOrderMapper {

    public static CreateOrderDto mapCreateOrderDto(CreateOrder createOrder){
        return new CreateOrderDto(
            createOrder.getId(),
            createOrder.getQuantity(),
            createOrder.getPricePerUnit(),
            createOrder.getType(),
            createOrder.getStockName(),
            createOrder.getStatus()
        );
    }


    public static CreateOrder mapToCreateOrder(CreateOrderDto createOrderDto){
        return new CreateOrder(
          createOrderDto.getId(),
          createOrderDto.getQuantity(),
          createOrderDto.getPricePerUnit(),
          createOrderDto.getType(),
          createOrderDto.getStockName(),
          createOrderDto.getStatus()
        );
    }
}
