package com.coded.spring.controller

import com.coded.spring.entity.UserEntity
import com.coded.spring.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController (private val userService: UserService){

    @PostMapping("/users/create")
    fun newUser(@RequestBody user: UserEntity): UserEntity {
    return userService.createUser(user)}

    @GetMapping("/users/v1/list")
    fun listUsers() = userService.findAllUsers()
}


