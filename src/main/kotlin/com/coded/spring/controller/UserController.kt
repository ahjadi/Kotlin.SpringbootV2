package com.coded.spring.controller

import com.coded.spring.entity.UserEntity
import com.coded.spring.service.ProfileRequest
import com.coded.spring.service.ProfileService
import com.coded.spring.service.UserRequest
import com.coded.spring.service.UserService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
@Tag(name = "CustomerAPI")
@RestController
class UserController(
    private val userService: UserService,
    private val encoder: PasswordEncoder,
    private val profileService: ProfileService,
    @Value("\${hello_world}")
    private val fromEnvVarMessage: String
) {

    @GetMapping("/hello")
    fun hello(): String {
        return fromEnvVarMessage
    }

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
        return try {
            profileService.save(request)
            ResponseEntity.ok().body("Profile saved successfully")
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().body("Error while saving profile ${e.message}")
        }

    }

    @GetMapping("/auth/profile/view/")
    fun viewProfile(): Any {
        return try {
            profileService.view()
        } catch (e: IllegalArgumentException) {
            "error: ${e.message}"
        }
    }

}




