package com.coded.spring.ordering


import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UsersController(
    private val usersRepository: UsersRepository
){

    @GetMapping("/users/v1/list")
    fun users() = usersRepository.findAll()
}