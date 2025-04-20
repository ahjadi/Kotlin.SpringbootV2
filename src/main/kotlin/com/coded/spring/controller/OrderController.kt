package com.coded.spring.controller

import com.coded.spring.entity.OrderEntity
import com.coded.spring.service.OrderService
import org.springframework.web.bind.annotation.*

@RestController
class OrderController(val orderService: OrderService){

    @PostMapping("/orders/v1/submit")
    fun submitOrder(@RequestBody orderRequest: OrderService.OrderRequestDTO) : OrderService.OrderResponseDTO{
        try {
            orderService.createOrder(orderRequest)
        } catch (e: IllegalArgumentException) {"error submitting the order: ${e.message}"}
        return OrderService.OrderResponseDTO(orderRequest.restaurant, orderRequest.items)
    }

    @GetMapping("/orders/user/orders/{userId}")
    fun listOrdersByUserId(@PathVariable userId: Long){
        try {
             orderService.listOrdersByUserID(userId)}
        catch (e: IllegalArgumentException) {"error occurred while listing orders: ${e.message}"}

        }
    }







