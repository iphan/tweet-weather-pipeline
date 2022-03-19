package com.bighealth.metrics.temperature

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

data class WeatherDto(val current: Weather, val location: Location)

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy::class)
data class Weather(val tempF: Double)

data class Location(val name: String,
                    val region: String,
                    val country: String,
                    val lat: Double,
                    val lon: Double)