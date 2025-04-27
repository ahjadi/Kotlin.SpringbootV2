package com.coded.spring.ordering

import com.coded.spring.authentication.jwt.JwtService
import com.coded.spring.entity.UserEntity
import com.coded.spring.repository.UserRepository
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.test.context.ActiveProfiles
import org.springframework.util.MultiValueMap
import kotlin.test.assertEquals

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ApplicationTests {
//    MOCK TRIGGER ASSERT
//    GIVEN WHEN THEN
    companion object {
        @JvmStatic
//        @BeforeAll
        fun setUp(
            @Autowired userRepository: UserRepository,
            @Autowired passwordEncoder: PasswordEncoder
        ) {

            userRepository.deleteAll()

            val testUser =
                UserEntity(
                    name = "coded",
                    email = "test@test.com",
                    username = "coded",
                    password = passwordEncoder.encode("Password123")
                )
            userRepository.save(testUser)
        }
    }

    // Tests go here
    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Test
    fun testHelloWorld(@Autowired jwtService: JwtService) {
        val token = jwtService.generateToken("coded")
        val headers = HttpHeaders(
            MultiValueMap.fromSingleValue(mapOf("Authorization" to "Bearer $token"))
        )
        val requestEntity = HttpEntity<String>(headers)

        val result = restTemplate.exchange(
            "/hello",
            HttpMethod.GET,
            requestEntity,
            String::class.java
        )
        assertEquals(HttpStatus.OK, result.statusCode)
        assertEquals("Hello World!", result.body)
    }
}



