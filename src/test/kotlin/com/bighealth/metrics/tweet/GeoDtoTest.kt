package com.bighealth.metrics.tweet

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.TestInstance
import kotlin.random.Random

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class GeoDtoTest {

    @Nested
    inner class CoordinatesWithTypeTest {
        @Test
        fun `parseCoordinates should return null if no coordinates `() {
            //given
            val sut = CoordinatesWithType("type", listOf())

            //when
            val coordinates = sut.parseCoordinates()

            //then
            assertNull(coordinates)
        }

        @Test
        fun `parseCoordinates should return null if one coordinate `() {
            //given
            val sut = CoordinatesWithType("type", listOf(Random.nextDouble()))

            //when
            val coordinates = sut.parseCoordinates()

            //then
            assertNull(coordinates)
        }

        @Test
        fun `parseCoordinates should return coordinates`() {
            //given
            val longitude = Random.nextDouble(-180.0, 180.0)
            val latitude = Random.nextDouble(-90.0, 90.0)
            val sut = CoordinatesWithType("type", listOf(longitude, latitude))

            //when
            val coordinates = sut.parseCoordinates()

            //then
            assertNotNull(coordinates)
            assertEquals(longitude, coordinates!!.longitude)
            assertEquals(latitude, coordinates!!.latitude)
        }
    }
}