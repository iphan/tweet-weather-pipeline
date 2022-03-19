package com.bighealth.metrics.tweet


data class ExtendedTweet(val data: Tweet, val includes: Places?) {
    fun coordinates(): Coordinates? {
        return data.geo.coordinates?.parseCoordinates() ?: includes?.places?.firstOrNull()?.geo?.coordinates()
    }
}

data class Tweet(val geo: Geo, val id: String)

