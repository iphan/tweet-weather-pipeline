package com.bighealth.metrics.tweet

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import kotlin.random.Random

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class CoordinatesTest {
    @Test
    fun `Coordinates parse should return null if no coordinates`() {
        //given
        val values = listOf<Double>()

        //when
        val coordinates = Coordinates.parse(values)

        //then
        assertNull(coordinates)
    }

    @Test
    fun `Coordinates parse should return null if one coordinate`() {
        //given
        val values = listOf(Random.nextDouble())

        //when
        val coordinates = Coordinates.parse(values)

        //then
        assertNull(coordinates)
    }

    @Test
    fun `Coordinates parse should return null with invalid longitude`() {
        //given
        val longitude = Random.nextDouble(200.0, 1000.0)
        val latitude = Random.nextDouble(-90.0, 90.0)
        val values = listOf(longitude, latitude)

        //when
        val coordinates = Coordinates.parse(values)

        //then
        assertNull(coordinates)
    }

    @Test
    fun `Coordinates parse should return null with invalid latitude`() {
        //given
        val longitude = Random.nextDouble(-180.0, 180.0)
        val latitude = Random.nextDouble(100.0, 200.0)
        val values = listOf(longitude, latitude)

        //when
        val coordinates = Coordinates.parse(values)

        //then
        assertNull(coordinates)
    }

    @Test
    fun `Coordinates parse should return coordinates`() {
        //given
        val longitude = Random.nextDouble(-180.0, 180.0)
        val latitude = Random.nextDouble(-90.0, 90.0)
        val values = listOf(longitude, latitude)

        //when
        val coordinates = Coordinates.parse(values)

        //then
        assertNotNull(coordinates)
        assertEquals(longitude, coordinates!!.longitude)
        assertEquals(latitude, coordinates!!.latitude)
    }
}