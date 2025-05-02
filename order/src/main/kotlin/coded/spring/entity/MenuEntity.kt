package coded.spring.entity

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "menu")
data class MenuEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var name: String,
    var price: BigDecimal
)
