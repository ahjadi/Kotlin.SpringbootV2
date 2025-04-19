package com.coded.spring.service

import com.coded.spring.entity.ProfileEntity
import com.coded.spring.repository.ProfileRepository
import com.coded.spring.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.lang.IllegalArgumentException


@Service
class ProfileService(private val profileRepository: ProfileRepository,
                     private val userRepository: UserRepository,) {



    fun save(profile: ProfileEntity) {
        val user = userRepository.findById(profile.userId).orElseThrow { IllegalArgumentException("No user with id ${profile.userId}") }

        require(profile.phoneNumber.length == 8 && profile.phoneNumber.all { it.isDigit() }) {"Phone number must be 8 digits" }
        require(profile.firstName.all { it.isLetter() }) {"First name must be letters" }
        require(profile.lastName.all { it.isLetter() }) {"Last name must be letters" }


        // To avoid the wrong user from profile saving
        val tokenUsername = SecurityContextHolder.getContext().authentication.name
        val requestUsername = userRepository.findById(profile.userId).get().username
        if (tokenUsername != requestUsername)
            throw ResponseStatusException(HttpStatus.FORBIDDEN, "Username mismatch")

        profileRepository.save(profile)
    }

}
data class ProfileRequest(
    val userId: Long,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String)