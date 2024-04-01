package com.travelhandsbackend.controller;


import com.travelhandsbackend.dto.CreateOrderDto;
import com.travelhandsbackend.service.CreateOrderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/order")
public class CreateOrderController {

    @Autowired
    private CreateOrderService createOrderService;

    @PostMapping("/create-order")
    public ResponseEntity<CreateOrderDto> createNewOrder(@RequestBody CreateOrderDto createOrderDto){
        CreateOrderDto savedCreateOrder = createOrderService.createNewOrder(createOrderDto);

        return new ResponseEntity<>(savedCreateOrder, HttpStatus.CREATED);
    }

    @GetMapping("/all-orders")
    public ResponseEntity<List<CreateOrderDto>> getAllOrders(){
        List<CreateOrderDto> allOrders = createOrderService.getAllOrders();

        return ResponseEntity.ok(allOrders);
    }


}
