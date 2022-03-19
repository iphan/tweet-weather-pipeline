package com.bighealth.metrics.temperature

import com.bighealth.metrics.tweet.ExtendedTweet
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class TemperatureService {

    private val log = LoggerFactory.getLogger(this::class.java)

    @Value("\${weather.api.key}")
    private lateinit var apiKey: String

    @Autowired
    private lateinit var temperatureClient: WebClient

    fun getTemperature(tweet: ExtendedTweet): Mono<WeatherDto> {
        val coordinates = tweet.coordinates() ?: return Mono.empty()

        log.debug("Getting temperature for {}", coordinates)
        return temperatureClient.get()
            .uri {
                it.queryParam("key", apiKey)
                    .queryParam("q", "${coordinates.latitude},${coordinates.longitude}")
                    .build()
            }
            .retrieve()
            .bodyToMono(WeatherDto::class.java)
            .doOnError { log.error("Couldn't retrieve temperature", it) }
    }
}