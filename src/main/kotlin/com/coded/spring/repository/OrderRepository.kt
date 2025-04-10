package com.coded.spring.repository

import com.coded.spring.entity.OrderEntity
import jakarta.inject.Named
import org.springframework.data.jpa.repository.JpaRepository

@Named
interface OrderRepository : JpaRepository<OrderEntity, Long>{
}