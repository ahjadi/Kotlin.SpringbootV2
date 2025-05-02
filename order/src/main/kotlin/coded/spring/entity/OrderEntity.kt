package coded.spring.entity

import jakarta.persistence.*

@Entity
@Table(name = "orders")
data class OrderEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var restaurant: String,

    var userId: Long
)
