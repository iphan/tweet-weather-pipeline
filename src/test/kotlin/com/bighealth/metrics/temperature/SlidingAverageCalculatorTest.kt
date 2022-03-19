package com.bighealth.metrics.temperature

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.TestInstance
import kotlin.random.Random

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class SlidingAverageCalculatorTest {

    @Test
    fun `SlidingAverageCalculator should keep all samples if fewer than window size`() {
        //given
        val windowSize = 10
        val sut = SlidingAverageCalculator(windowSize)
        val numOfSamples = 6
        val samples =  mutableListOf<Double>()

        //when
        for (i in 1..numOfSamples) {
            val sample = Random.nextDouble()
            sut.add(sample)
            samples.add(sample)
        }

        //then
        assertEquals(numOfSamples, sut.getSampleCount())
        assertEquals(samples.average(), sut.computeSlidingAverage())
    }

    @Test
    fun `SlidingAverageCalculator should keep only most recent samples if more than window size`() {
        //given
        val windowSize = 10
        val sut = SlidingAverageCalculator(windowSize)
        val numOfSamples = 15
        val samples =  mutableListOf<Double>()

        //when
        for (i in 1..numOfSamples) {
            val sample = Random.nextDouble()
            sut.add(sample)
            samples.add(sample)
        }

        //then
        assertEquals(windowSize, sut.getSampleCount())
        assertEquals(samples.subList(numOfSamples - windowSize, numOfSamples).average(), sut.computeSlidingAverage())
    }
}