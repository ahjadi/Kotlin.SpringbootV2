package coded.spring.client


import com.ali.authentication.CheckTokenResponse
import jakarta.inject.Named
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.*
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.exchange

@Named
class AuthenticationClient {

    fun checkToken(token: String): CheckTokenResponse {
        val restTemplate = RestTemplate()
        val url = "http://localhost:8080/auth/check-token"
        val response = restTemplate.exchange<CheckTokenResponse>(
            url = url,
            method = HttpMethod.POST,
            requestEntity = HttpEntity<String>(
                MultiValueMap.fromMultiValue(mapOf("Authorization" to listOf("Bearer $token")))
            ),
            object : ParameterizedTypeReference<CheckTokenResponse>() {
            }
        )
        return response.body ?: throw IllegalStateException("Check token response has no body ...")
    }


}