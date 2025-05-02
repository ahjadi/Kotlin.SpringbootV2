package coded.spring.entity

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "items")
data class ItemEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var name: String,
    var quantity: Int,
    var price :   BigDecimal,

    var orderId: Long

)


