package coded.spring.service

import coded.spring.entity.ItemEntity
import coded.spring.entity.OrderEntity
import coded.spring.repository.ItemRepository
import coded.spring.repository.OrderRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class OrderService(
    private var orderRepository: OrderRepository,
    private var itemRepository: ItemRepository,
    @Value("\${discount.feature}")
    val discount: Boolean
) {

     fun createOrder(request: OrderRequest) {

         val savedOrder =orderRepository.save(OrderEntity(
             restaurant = request.restaurant,
             userId = request.userId))



         if (discount){
             itemRepository.saveAll(request.items.map {
                 ItemEntity(
                     name = it.name,
                     quantity = it.quantity,
                     price = it.price.multiply(BigDecimal(0.8)),
                     orderId = savedOrder.id!!
                 )
             })
         }
         else {
             itemRepository.saveAll(request.items.map {
                 ItemEntity(
                     name = it.name,
                     quantity = it.quantity,
                     price = it.price,
                     orderId = savedOrder.id!!
                 )
             })
         }

     }


    fun listOrdersByUserID(userId: Long) {
        orderRepository.findAllByUserId(userId)
    }


    data class ItemDto(
        val name: String,
        val quantity: Int,
        val price: BigDecimal
    )

    data class OrderRequest(
        val userId: Long,
        val restaurant: String,
        val items: MutableList<ItemDto>
    )

    data class OrderResponse(
        val restaurant: String,
        val items: List<ItemDto>
    )
}