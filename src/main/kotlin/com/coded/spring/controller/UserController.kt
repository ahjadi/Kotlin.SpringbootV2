package com.coded.spring.controller

import com.coded.spring.entity.ProfileEntity
import com.coded.spring.entity.UserEntity
import com.coded.spring.service.ProfileRequest
import com.coded.spring.service.ProfileService
import com.coded.spring.service.UserRequest
import com.coded.spring.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService,
    private val encoder: PasswordEncoder,
    private val profileService: ProfileService
) {

    @GetMapping("/hello")
    fun hello() = """
        <html>
            <body>
                <p style="color: #00008B;">hello yo</p>
            </body>
        </html>
     
    """.trimIndent()

    @PostMapping("/public/users/create")
    fun newUser(@RequestBody userRequest: UserRequest): Any {
        userService.validatePassword(userRequest.password)
        return userService.createUser(
            UserEntity(
                name = userRequest.name,
                email = userRequest.email,
                username = userRequest.username,
                password = encoder.encode(userRequest.password)
            )
        )
    }

    @GetMapping("/users/v1/list")
    fun listUsers() = userService.findAllUsers()

    @PostMapping("/auth/profile/save")
    fun saveProfile(@RequestBody request: ProfileRequest): Any {

        val newProfile = ProfileEntity(
            firstName = request.firstName,
            lastName = request.lastName, phoneNumber = request.phoneNumber,
            userId = request.userId
        )
        profileService.save(newProfile)
        return ResponseEntity.ok().body("Success")
    }

}




