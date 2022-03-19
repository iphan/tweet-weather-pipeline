package com.bighealth.metrics

import com.bighealth.metrics.file.FileWriter
import com.bighealth.metrics.temperature.SlidingAverageCalculator
import com.bighealth.metrics.temperature.TemperatureService
import com.bighealth.metrics.tweet.TwitterService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import javax.annotation.PreDestroy

@Component
class PipelineRunner: ApplicationRunner {

    private val log = LoggerFactory.getLogger(this::class.java)

    @Value("\${temperature.sliding.average.window.size}")
    private val temperatureSlidingAverageWindowSize: Int = 10

    private val slidingAverageCalculator = SlidingAverageCalculator(temperatureSlidingAverageWindowSize)

    @Autowired
    private lateinit var twitterService: TwitterService

    @Autowired
    private lateinit var temperatureService: TemperatureService

    @Autowired
    private lateinit var rawTemperatureFileWriter: FileWriter

    @Autowired
    private lateinit var temperatureSlidingAverageFileWriter: FileWriter

    override fun run(args: ApplicationArguments?) {
        twitterService.geoLocalizedTweetStream()
            .flatMap { temperatureService.getTemperature(it) }
            .doOnNext {
                log.debug("Temperature {}", it)
                rawTemperatureFileWriter.writeLine(it.current.tempF.toString())
            }
            .subscribe {
                slidingAverageCalculator.add(it.current.tempF)
                val slidingAverage = slidingAverageCalculator.computeSlidingAverage()
                temperatureSlidingAverageFileWriter.writeLine(slidingAverage.toString())
                log.debug("Sliding average Temperature {}", slidingAverage)
            }
    }

    @PreDestroy
    fun close() {
        rawTemperatureFileWriter.close()
        temperatureSlidingAverageFileWriter.close()
    }
}