package com.bighealth.metrics.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WeatherWebClientConfig {

    @Value("\${weather.current.temperature.url}")
    private lateinit var temperatureUrl: String

    @Bean
    fun temperatureClient(): WebClient {
        return WebClient.builder()
            .baseUrl(temperatureUrl)
            .build()
    }

}