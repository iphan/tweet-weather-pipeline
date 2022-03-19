package com.bighealth.metrics.tweet

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import kotlin.random.Random

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class PlaceDtoTest {
    @Nested
    inner class PolygonTest {
        @Test
        fun `parseCoordinates should return null if no coordinates`() {
            //given
            val sut = Polygon("type", listOf())

            //when
            val coordinates = sut.coordinates()

            //then
            Assertions.assertNull(coordinates)
        }

        @Test
        fun `parseCoordinates should return null if only one coordinate`() {
            //given
            val sut = Polygon("type", listOf(Random.nextDouble()))

            //when
            val coordinates = sut.coordinates()

            //then
            Assertions.assertNull(coordinates)
        }

        @Test
        fun `parseCoordinates should return coordinates if two coordinates`() {
            //given
            val longitude = Random.nextDouble(-180.0, 180.0)
            val latitude = Random.nextDouble(-90.0, 90.0)
            val sut = Polygon("type", listOf(longitude, latitude))

            //when
            val coordinates = sut.coordinates()

            //then
            Assertions.assertNotNull(coordinates)
            Assertions.assertEquals(longitude, coordinates!!.longitude)
            Assertions.assertEquals(latitude, coordinates!!.latitude)
        }

        @Test
        fun `parseCoordinates should return coordinates of centroid if more than 2 coordinates`() {
            //given
            val numberOfCoordinates = Random.nextInt(1, 100)
            val longitudes = (1..numberOfCoordinates).map { Random.nextDouble(-180.0, 180.0) }
            val latitudes = (1..numberOfCoordinates).map { Random.nextDouble(-90.0, 90.0) }
            val sut = Polygon("type", longitudes.zip(latitudes).flatMap { listOf(it.first, it.second)})

            //when
            val coordinates = sut.coordinates()

            //then
            Assertions.assertNotNull(coordinates)
            Assertions.assertEquals(longitudes.average(), coordinates!!.longitude)
            Assertions.assertEquals(latitudes.average(), coordinates!!.latitude)
        }
    }
}