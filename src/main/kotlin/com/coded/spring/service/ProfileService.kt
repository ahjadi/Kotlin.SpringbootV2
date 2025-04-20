package com.coded.spring.service

import com.coded.spring.entity.ProfileEntity
import com.coded.spring.repository.ProfileRepository
import com.coded.spring.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class ProfileService(
    private val profileRepository: ProfileRepository,
    private val userRepository: UserRepository,
) {


    fun save(profile: ProfileRequest) {
        val userName = SecurityContextHolder.getContext().authentication.name
        val userId = userRepository.findByUsername(userName)?.id
            ?: throw IllegalArgumentException("User Not Found")

        require(profile.phoneNumber.length == 8 && profile.phoneNumber.all { it.isDigit() }) { "Phone number must be 8 digits" }
        require(profile.firstName.all { it.isLetter() }) { "First name must be letters" }
        require(profile.lastName.all { it.isLetter() }) { "Last name must be letters" }


        // To avoid the wrong user from profile saving
        val tokenUsername = SecurityContextHolder.getContext().authentication.name
        val requestUsername = userRepository.findById(userId).get().username
        if (tokenUsername != requestUsername)
            throw ResponseStatusException(HttpStatus.FORBIDDEN, "Username mismatch")
        val newProfile = ProfileEntity( userId = userId, firstName = profile.firstName, lastName = profile.lastName, phoneNumber = profile.phoneNumber)
        profileRepository.save(newProfile)
    }

    fun view() : ProfileResponse {
        val userName = SecurityContextHolder.getContext().authentication.name
        val userId = userRepository.findByUsername(userName)?.id
            ?: throw IllegalArgumentException("User Not Found")
        val profile = profileRepository.findByUserId(userId) ?: throw IllegalArgumentException("No profile with id $userId")
       return ProfileResponse(profile.firstName, profile.lastName,profile.phoneNumber)
    }


}

data class ProfileRequest(
    val firstName: String,
    val lastName: String,
    val phoneNumber: String
)

data class ProfileResponse(
    val firstName: String,
    val lastName: String,
    val phoneNumber: String
)