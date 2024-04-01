package com.travelhandsbackend.service;

import com.travelhandsbackend.dto.CreateOrderDto;

import java.util.List;

public interface CreateOrderService {
    CreateOrderDto createNewOrder(CreateOrderDto createOrderDto);

    List<CreateOrderDto> getAllOrders();
}
