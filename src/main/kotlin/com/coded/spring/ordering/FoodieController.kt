package com.coded.spring.ordering

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class FoodieController(val ordersRepository: OrderRepository) {

    // Exercise 1
    @GetMapping("/welcome")
    fun chooseDeliveryOrPickUp() = "Delivery or Pick-Up?"

    // Exercise 2
    @PostMapping("/order")
    fun makeAnOrder(@RequestBody request: OrderRequest): Order {
        val newOrder = Order(
            user = request.user,
            restaurant = request.restaurant,
            items = request.items
        )
        return ordersRepository.save(newOrder)
    }

    @GetMapping("/order")
    fun getAllOrders() = ordersRepository.findAll()
}
data class OrderRequest(
    val user: String,
    val restaurant: String,
    val items: MutableList<String?>
)
