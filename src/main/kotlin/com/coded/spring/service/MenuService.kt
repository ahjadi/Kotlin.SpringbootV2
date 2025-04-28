package com.coded.spring.service

import com.coded.spring.controller.MenuRequest
import com.coded.spring.entity.MenuEntity
import com.coded.spring.repository.MenuRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class MenuService(
    val menuRepository: MenuRepository,
){

    fun addItems(menu: MenuRequest) {

        menuRepository.save(MenuEntity(
            name = menu.name,
            price = menu.price))
    }

    fun listMenuItems() = menuRepository.findAll()
}