package com.bighealth.metrics.tweet

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy::class)
data class Geo(val placeId: String?, val coordinates: CoordinatesWithType?)

data class CoordinatesWithType(val type: String, val coordinates: List<Double>) {
    fun parseCoordinates(): Coordinates? {
        return Coordinates.parse(coordinates)
    }
}