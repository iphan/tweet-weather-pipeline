package com.bighealth.metrics.tweet

import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux


@Service
class TwitterService {

    private val log = LoggerFactory.getLogger(this::class.java)

    @Autowired
    private lateinit var twitterSampleStreamClient: WebClient

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    fun geoLocalizedTweetStream(): Flux<ExtendedTweet> {
        log.info("starting tweet stream")
        return twitterSampleStreamClient.get()
            .retrieve()
            .bodyToFlux(String::class.java)
            .filter { it != "" }
            .map { objectMapper.readValue(it, ExtendedTweet::class.java) }
            .doOnError { log.error("Couldn't parse tweet", it) }
            .filter { it != null && it.data.geo.placeId != null }
            .doOnNext { log.debug("Parsed tweet $it") }

    }


}

