package com.travelhandsbackend.service.impl;

import com.travelhandsbackend.dto.CreateOrderDto;
import com.travelhandsbackend.dto.mapper.CreateOrderMapper;
import com.travelhandsbackend.entity.CreateOrder;
import com.travelhandsbackend.repository.CreateOrderRepository;
import com.travelhandsbackend.service.CreateOrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CreateOrderServiceImpl implements CreateOrderService {

    private CreateOrderRepository createOrderRepository;

    @Override
    public CreateOrderDto createNewOrder(CreateOrderDto createOrderDto) {
        CreateOrder createOrder = CreateOrderMapper.mapToCreateOrder(createOrderDto);
        CreateOrder saveCreateOrder = createOrderRepository.save(createOrder);

        return CreateOrderMapper.mapCreateOrderDto(saveCreateOrder);
    }

    @Override
    public List<CreateOrderDto> getAllOrders() {
        List<CreateOrder> allCreateOrders = createOrderRepository.findAll();

        return allCreateOrders.stream().map((order) -> CreateOrderMapper.mapCreateOrderDto(order)).collect(Collectors.toList());
    }
}
