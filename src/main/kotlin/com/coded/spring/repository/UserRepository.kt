package com.coded.spring.repository


import com.coded.spring.entity.UserEntity
import jakarta.inject.Named
import org.springframework.data.jpa.repository.JpaRepository

@Named
interface UserRepository : JpaRepository<UserEntity, Long>
