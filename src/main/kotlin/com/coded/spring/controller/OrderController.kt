package com.coded.spring.controller

import com.coded.spring.entity.OrderEntity
import com.coded.spring.service.OrderService
import org.springframework.web.bind.annotation.*

@RestController
class OrderController(val orderService: OrderService){

    @PostMapping("/orders/v1/submit")
    fun submitOrder(@RequestBody orderRequest: OrderService.OrderRequestDTO) : OrderService.OrderResponseDTO{
        orderService.createOrder(orderRequest)
        return OrderService.OrderResponseDTO(orderRequest.restaurant, orderRequest.items)
    }

    @GetMapping("/orders/user/orders/{userId}")
    fun listOrdersByUserId(@PathVariable userId: Long): List<OrderEntity> {
        return orderService.listOrdersByUserID(userId)}

    }







