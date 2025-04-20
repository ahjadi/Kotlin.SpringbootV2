package com.coded.spring.service

import com.coded.spring.entity.ItemEntity
import com.coded.spring.entity.OrderEntity
import com.coded.spring.repository.ItemRepository
import com.coded.spring.repository.OrderRepository
import com.coded.spring.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.math.BigDecimal

@Service
class OrderService(
    private var orderRepository: OrderRepository,
    private var itemRepository: ItemRepository,
    private var userRepository: UserRepository
) {

    fun createOrder(request: OrderRequest) {
        val userName = SecurityContextHolder.getContext().authentication.name
        val userId = userRepository.findByUsername(userName)?.id
            ?: throw IllegalArgumentException("User Not Found")

        val tokenUsername = SecurityContextHolder.getContext().authentication.name
        val requestUsername = userRepository.findById(userId).get().username
        if (tokenUsername != requestUsername)
            throw ResponseStatusException(HttpStatus.FORBIDDEN, "Username mismatch")

        val order = OrderEntity(
            restaurant = request.restaurant,
            userId = userId
        )
        val savedOrder = orderRepository.save(order)
        val items = request.items.map {
            ItemEntity(
                name = it.name,
                quantity = it.quantity,
                price = it.price,
                orderId = savedOrder.id!!
            )
        }

        itemRepository.saveAll(items)
    }

    fun listOrdersByUserID(userId: Long) {
        val user = userRepository.findById(userId).orElseThrow { IllegalArgumentException("User Not Found") }
        orderRepository.findAllByUserId(userId)
    }


    data class ItemDTO(
        val name: String,
        val quantity: Int,
        val price: BigDecimal
    )

    data class OrderRequest(
        val restaurant: String,
        val items: MutableList<ItemDTO>
    )

    data class OrderResponse(
        val restaurant: String,
        val items: List<ItemDTO>
    )
}