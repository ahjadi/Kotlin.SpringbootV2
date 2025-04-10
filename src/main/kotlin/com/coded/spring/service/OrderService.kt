package com.coded.spring.service

import com.coded.spring.entity.ItemEntity
import com.coded.spring.entity.OrderEntity
import com.coded.spring.repository.ItemRepository
import com.coded.spring.repository.OrderRepository
import com.coded.spring.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class OrderService (
    private var orderRepository: OrderRepository,
    private var userRepository: UserRepository,
    private var itemRepository: ItemRepository
){

    fun createOrder(userId: Long, restaurant: String, items: MutableList<ItemEntity>) {
        val user = userRepository.findById(userId).get()
        val newOrder = OrderEntity(
            user = user, restaurant = restaurant,
            id = user.id,
            items = items
        )
        orderRepository.save(newOrder)
        itemRepository.saveAll(items)
    }

    fun listOrders(){

    }


}