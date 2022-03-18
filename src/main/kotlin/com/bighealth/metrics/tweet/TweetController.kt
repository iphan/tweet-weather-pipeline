package com.bighealth.metrics.tweet

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TweetController {
    @GetMapping("hello")
    fun getHello(): ResponseEntity<String> {
        return ResponseEntity.ok("hello")
    }
}