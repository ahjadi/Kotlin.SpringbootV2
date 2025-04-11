package com.coded.spring.repository

import com.coded.spring.entity.OrderEntity
import com.coded.spring.entity.UserEntity
import jakarta.inject.Named
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : JpaRepository<OrderEntity, Long>{
    fun findAllByUser(user: UserEntity): List<OrderEntity>
    fun findAllByUserId(userId: Long): List<OrderEntity>
}