package com.bighealth.metrics.tweet

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

data class Places(val places: List<Place>)

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy::class)
data class Place(val id: String, val fullName: String?, val placeType: String, val geo: Polygon?)

data class Polygon(val type: String, val bbox: List<Double>) {
    fun coordinates(): Coordinates? {
        if (bbox.size < 2) return null

        val locations = bbox.chunked(2)
            .mapNotNull { Coordinates.parse(it) }

        var longitudeSum = 0.0
        var latitudeSum = 0.0
        locations.forEach {
            longitudeSum += it.longitude
            latitudeSum += it.latitude
        }
        return Coordinates(longitudeSum / locations.size, latitudeSum / locations.size)
    }
}