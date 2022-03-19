package com.bighealth.metrics.configuration

import com.bighealth.metrics.file.FileWriter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FileConfiguration {

    @Value("\${temperature.output.filename}")
    private lateinit var temperatureOutputFilename: String

    @Value("\${temperature.sliding.average.output.filename}")
    private lateinit var temperatureSlidingAverageOutputFilename: String

    @Bean
    fun rawTemperatureFileWriter(): FileWriter {
        return FileWriter(temperatureOutputFilename)
    }

    @Bean
    fun temperatureSlidingAverageFileWriter(): FileWriter {
        return FileWriter(temperatureSlidingAverageOutputFilename)
    }
}