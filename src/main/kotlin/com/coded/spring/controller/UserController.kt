package com.coded.spring.controller

import com.coded.spring.entity.UserEntity
import com.coded.spring.service.UserService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private val userService: UserService,
    private val encoder: PasswordEncoder) {

    @GetMapping("/hello")
    fun hello()= """
        <html>
            <body>
                <p style="color: #00008B;">hello yo</p>
            </body>
        </html>
     
    """.trimIndent()

    @PostMapping("/public/users/create")
    fun newUser(@RequestBody userRequest: UserRequest): Any {
        userService.validatePassword(userRequest.password)
        return userService.createUser(UserEntity(name = userRequest.name, email = userRequest.email, username = userRequest.username, password = encoder.encode(userRequest.password) ))
    }

    @GetMapping("/users/v1/list")
    fun listUsers() = userService.findAllUsers()
}

data class UserRequest(
    val name: String,
    val email: String,
    val username: String,
    val password: String
)


