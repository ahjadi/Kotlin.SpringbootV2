package coded.spring.controller

import coded.spring.service.OrderService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*
@Tag(name = "OrderAPI")
@RestController
class OrderController(val orderService: OrderService) {

    @PostMapping("/orders/v1/submit")
    fun submitOrder(@RequestBody orderRequest: OrderService.OrderRequest): OrderService.OrderResponse {
        try {
            orderService.createOrder(orderRequest)
        } catch (e: IllegalArgumentException) {
            "error submitting the order: ${e.message}"
        }
        return OrderService.OrderResponse(orderRequest.restaurant, orderRequest.items)
    }

    @GetMapping("/orders/user/orders/{userId}")
    fun listOrdersByUserId(@PathVariable userId: Long) {
        try {
            orderService.listOrdersByUserID(userId)
        } catch (e: IllegalArgumentException) {
            "error occurred while listing orders: ${e.message}"
        }

    }
}







