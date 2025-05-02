package coded.spring.repository

import coded.spring.entity.OrderEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : JpaRepository<OrderEntity, Long>{
    fun findAllByUserId(userId: Long): List<OrderEntity>
}