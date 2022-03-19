package com.bighealth.metrics.temperature

import java.util.*

class SlidingAverageCalculator(private val windowSize: Int) {

    private val samples: LinkedList<Double> = LinkedList()

    fun computeSlidingAverage(): Double {
        return samples.average()
    }

    fun add(value: Double) {
        if (samples.size == windowSize) {
            samples.removeFirst()
        }
        samples.addLast(value)
    }

    fun getSampleCount(): Int {
        return samples.size
    }
}
