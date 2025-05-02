package com.ali.authentication.user


import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {


    fun findAllUsers() = userRepository.findAll()

    fun createUser(user: UserEntity) = userRepository.save(user)

    fun validatePassword(password: String) {
        require(password.length >= 6) { "Password must be at least 6 characters long" }
        require(password.any { it.isUpperCase() }) { "Password must contain at least one uppercase letter" }
        require(password.any { it.isDigit() }) { "Password must contain at least one number" }
    }


    fun findByUsername(username: String): Long =
        userRepository.findByUsername(username)?.id ?: throw IllegalStateException("User has no id...")

}

data class UserRequest(
    val name: String,
    val email: String,
    val username: String,
    val password: String
)