package com.coded.spring.service

import com.coded.spring.entity.MenuEntity
import com.coded.spring.repository.MenuRepository
import org.springframework.stereotype.Service

@Service
class MenuService(val menuRepository: MenuRepository){

    fun addItems(menuEntity: MenuEntity) = menuRepository.save(menuEntity)

    fun listMenuItems() = menuRepository.findAll()
}