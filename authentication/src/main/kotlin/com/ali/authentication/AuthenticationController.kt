package com.ali.authentication


import com.ali.authentication.jwt.JwtService
import com.ali.authentication.user.UserService
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.*
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.security.Principal


@RestController
@RequestMapping("/auth")
class AuthenticationController(
    private val authenticationManager: AuthenticationManager,
    private val userDetailsService: UserDetailsService,
    private val jwtService: JwtService,
    private val userService: UserService
) {

    @PostMapping("/login")
    fun login(@RequestBody authRequest: AuthenticationRequest): AuthenticationResponse {
        val authToken = UsernamePasswordAuthenticationToken(authRequest.username, authRequest.password)
        val authentication = authenticationManager.authenticate(authToken)

        if (authentication.isAuthenticated) {
            val userDetails = userDetailsService.loadUserByUsername(authRequest.username)
            val token = jwtService.generateToken(userDetails.username)
            return AuthenticationResponse (token)
        } else {
            throw UsernameNotFoundException("Invalid user request!")
        }
    }

    @PostMapping("/check-token")
    fun checkToken(
        principal: Principal
    ): CheckTokenResponse {
        return CheckTokenResponse(
            userId = userService.findByUsername(principal.name)
        )
    }
//    @PostMapping("/check-token")
//    fun checkToken(): CheckTokenResponse {
//        val auth = SecurityContextHolder.getContext().authentication
//        if (auth == null || !auth.isAuthenticated) {
//            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token")
//        }
//        val username = auth.name
//        val userId = userService.findByUsername(username)
//        return CheckTokenResponse(userId)
//    }
}

data class CheckTokenResponse(
    val userId: Long
)

data class AuthenticationRequest(
    val username: String,
    val password: String
)

data class AuthenticationResponse(
    val token: String
)