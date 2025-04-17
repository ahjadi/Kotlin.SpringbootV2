package com.coded.spring.service

//import com.coded.spring.entity.UserEntity
import com.coded.spring.entity.UserEntity
import com.coded.spring.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService (private val userRepository: UserRepository){

    fun findAllUsers() = userRepository.findAll()

    fun createUser(user: UserEntity) = userRepository.save(user)

    fun validatePassword(password: String) {
        require(password.length >= 6) { "Password must be at least 6 characters long" }
        require(password.any { it.isUpperCase() }) { "Password must contain at least one uppercase letter" }
        require(password.any { it.isDigit() }) { "Password must contain at least one number" }
    }

}