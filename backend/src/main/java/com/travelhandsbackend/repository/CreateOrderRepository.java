package com.travelhandsbackend.repository;

import com.travelhandsbackend.entity.CreateOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreateOrderRepository extends JpaRepository<CreateOrder,Long> {
}
