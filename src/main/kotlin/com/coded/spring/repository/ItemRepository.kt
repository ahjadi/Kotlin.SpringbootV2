package com.coded.spring.repository

import com.coded.spring.entity.ItemEntity
import jakarta.inject.Named
import org.springframework.data.jpa.repository.JpaRepository

@Named
interface ItemRepository : JpaRepository<ItemEntity, Long>