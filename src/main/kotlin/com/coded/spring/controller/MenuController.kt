package com.coded.spring.controller

import com.coded.spring.entity.MenuEntity
import com.coded.spring.service.MenuService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
@Tag(name = "MenuAPI")
@RestController
class MenuController(val menuService: MenuService) {

    @PostMapping("/menu/add")
    fun addItemsToMenu(@RequestBody menuRequest: MenuRequest) : Any {

        return ResponseEntity.ok().body(menuService.addItems(menuRequest))
    }

    @GetMapping("/public/menu/list")
    fun listItems() = menuService.listMenuItems()
}

data class MenuRequest(
    val name: String,
    val price: BigDecimal
)
