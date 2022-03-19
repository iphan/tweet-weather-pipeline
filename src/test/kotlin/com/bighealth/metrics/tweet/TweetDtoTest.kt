package com.bighealth.metrics.tweet

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.random.Random

internal class TweetDtoTest {
    @Nested
    inner class ExtendedTweetTest {
        @Test
        fun `coordinates() should return null if no coordinates`() {
            //given
            val sut = ExtendedTweet(Tweet(Geo(null, null), "id"), null)

            //when
            val coordinates = sut.coordinates()

            //then
            Assertions.assertNull(coordinates)
        }

        @Test
        fun `coordinates() should return valid coordinates if geo has values`() {
            //given
            val longitude = Random.nextDouble(-180.0, 180.0)
            val latitude = Random.nextDouble(-90.0, 90.0)
            val values = CoordinatesWithType("placeid", listOf(longitude, latitude))
            val sut = ExtendedTweet(Tweet(Geo(null, values), "id"), null)

            //when
            val coordinates = sut.coordinates()

            //then
            Assertions.assertNotNull(coordinates)
            Assertions.assertEquals(longitude, coordinates!!.longitude)
            Assertions.assertEquals(latitude, coordinates!!.latitude)
        }

        @Test
        fun `coordinates() should return valid coordinates if polygon has values`() {
            //given
            val longitude = Random.nextDouble(-180.0, 180.0)
            val latitude = Random.nextDouble(-90.0, 90.0)
            val polygon = Polygon("placeid", listOf(longitude, latitude))
            val place = Place("placeid", "name", "placeType", polygon)
            val sut = ExtendedTweet(Tweet(Geo(null, null), "id"), Places(listOf(place)))

            //when
            val coordinates = sut.coordinates()

            //then
            Assertions.assertNotNull(coordinates)
            Assertions.assertEquals(longitude, coordinates!!.longitude)
            Assertions.assertEquals(latitude, coordinates!!.latitude)
        }

        @Test
        fun `coordinates() should return geo's coordinates if both geo and polygon have values`() {
            //given
            val longitude = Random.nextDouble(-180.0, 180.0)
            val latitude = Random.nextDouble(-90.0, 90.0)
            val values = CoordinatesWithType("placeid", listOf(longitude, latitude))
            val polygon = Polygon("placeid", listOf(Random.nextDouble(-180.0, 180.0), Random.nextDouble(-90.0, 90.0)))
            val place = Place("placeid", "name", "placeType", polygon)
            val sut = ExtendedTweet(Tweet(Geo(null, values), "id"), Places(listOf(place)))

            //when
            val coordinates = sut.coordinates()

            //then
            Assertions.assertNotNull(coordinates)
            Assertions.assertEquals(longitude, coordinates!!.longitude)
            Assertions.assertEquals(latitude, coordinates!!.latitude)
        }
    }
}