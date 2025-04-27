package com.coded.spring

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
class LoggingFilter : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val cachedRequest= ContentCachingRequestWrapper(request)
        val cachedResponse= ContentCachingResponseWrapper(response)
        filterChain.doFilter(cachedRequest, cachedResponse)

        logRequest(cachedRequest)
        logResponse(cachedResponse)
        cachedResponse.copyBodyToResponse()

    }

    private fun logRequest(request: ContentCachingRequestWrapper) {
        val requestBody = String(request.contentAsByteArray)
        // Thread safe logging for multi processing
        logger.info("Request: method=${request.method}, uri=${request.requestURI}, body=$requestBody")
    }

    private fun logResponse(response: ContentCachingResponseWrapper) {
        val responseBody = String(response.contentAsByteArray)
        logger.info("Response: status=${response.status}, body=$responseBody")
    }


}
