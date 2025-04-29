package com.coded.spring.service

import com.coded.spring.controller.MenuRequest
import com.coded.spring.entity.MenuEntity
import com.coded.spring.repository.MenuRepository
import com.coded.spring.serverCache
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

    fun listMenuItems(search: String?): List<MenuEntity> {

        val cachedMenu = menuCache["menu"]

        if (!cachedMenu.isNullOrEmpty())
        {
            println("\nFrom cache\n")

             if (search != null)
                 return cachedMenu.filter { it.name.trim().lowercase().contains(search, true) }
            else
                return cachedMenu
        }
        val uncachedMenu = menuRepository.findAll()
        menuCache["menu"] = uncachedMenu
        if (search != null)
            return uncachedMenu.filter { it.name.trim().lowercase().contains(search, true)  }
        else
            return uncachedMenu
    }

    val menuCache = serverCache.getMap< String,List<MenuEntity>>("menu")
}


