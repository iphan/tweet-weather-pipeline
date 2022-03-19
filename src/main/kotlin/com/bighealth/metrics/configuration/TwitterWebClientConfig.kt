package com.bighealth.metrics.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class TwitterWebClientConfig {

    @Value("\${twitter.sample.stream.url}")
    private lateinit var sampleStreamUrl: String

    @Value("\${twitter.auth.token}")
    private lateinit var authToken: String

    @Bean
    fun twitterSampleStreamClient(): WebClient {
        return WebClient.builder()
            .baseUrl(sampleStreamUrl)
            .defaultHeader(HttpHeaders.AUTHORIZATION, authToken)
            .build()
    }


}