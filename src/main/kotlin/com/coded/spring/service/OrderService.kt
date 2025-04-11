package com.coded.spring.service

import com.coded.spring.entity.ItemEntity
import com.coded.spring.entity.OrderEntity
import com.coded.spring.entity.UserEntity
import com.coded.spring.repository.ItemRepository
import com.coded.spring.repository.OrderRepository
import com.coded.spring.repository.UserRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class OrderService (
    private var orderRepository: OrderRepository,
    private var userRepository: UserRepository,
    private var itemRepository: ItemRepository
){

    fun createOrder(orderRequest: OrderRequestDTO) {
        val user = userRepository.findById(orderRequest.userId).get()
        val newOrder = OrderEntity(
            user = user, restaurant = orderRequest.restaurant,
            items = orderRequest.items
        )
        orderRepository.save(newOrder)
        itemRepository.saveAll(orderRequest.items)
    }

    fun listOrdersByUserID(user_ID: Long) = orderRepository.findAllByUserId(user_ID)


    data class ItemDTO(
        val name: String,
        val quantity: Int,
        val price: BigDecimal
    )
    data class OrderRequestDTO(
        val userId: Long,
        val restaurant: String,
        val items: MutableList<ItemEntity>
    )
}