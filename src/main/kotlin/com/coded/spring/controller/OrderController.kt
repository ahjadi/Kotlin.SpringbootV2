package com.coded.spring.controller

import com.coded.spring.entity.OrderEntity
import com.coded.spring.service.OrderService
import org.springframework.web.bind.annotation.*

@RestController
class OrderController(val orderService: OrderService){

    @PostMapping("/orders/v1/submit")
    fun submitOrder(@RequestBody orderRequest: OrderService.OrderRequestDTO) {
        orderService.createOrder(orderRequest)
    }

    @GetMapping("/orders/user/orders/{userId}")
    fun listOrdersByUserId(@PathVariable userId: Long): List<OrderEntity> {
        return orderService.listOrdersByUserID(userId)}

    }







