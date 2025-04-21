package com.coded.spring.ordering

import com.coded.spring.service.OrderService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import kotlin.test.assertEquals

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {

	@Autowired
	lateinit var restTemplate: TestRestTemplate

	@Test
	fun testHelloWorld(){
		val result = restTemplate.getForEntity("/hello", String::class.java)
		val expected = "Hello World!"

		assertEquals(HttpStatus.OK, result.statusCode)
		assertEquals(expected, result.body)
	}
		@Test
	fun testSubmitOrder(){

		val result = restTemplate.postForEntity("/orders/v1/submit",  )

	}


}
