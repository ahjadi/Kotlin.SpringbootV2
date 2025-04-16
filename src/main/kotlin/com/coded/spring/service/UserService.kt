package com.coded.spring.service

//import com.coded.spring.entity.UserEntity
import com.coded.spring.entity.UserEntity
import com.coded.spring.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService (private val userRepository: UserRepository){

    fun findAllUsers() = userRepository.findAll()

    fun createUser(user: UserEntity) = userRepository.save(user)


}