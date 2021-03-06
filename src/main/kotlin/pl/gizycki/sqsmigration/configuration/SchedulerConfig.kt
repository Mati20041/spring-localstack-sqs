package pl.gizycki.sqsmigration.configuration

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import pl.gizycki.sqsmigration.SqsProducer
import java.util.concurrent.atomic.AtomicInteger

@Configuration
@ConditionalOnProperty("sqs.schedule")
@EnableScheduling
class SchedulerConfig(val sqsProducer: SqsProducer) {
    val counter = AtomicInteger(0)


    @Scheduled(fixedRate = 10000) // ten seconds
    fun sendMsg() {
        sqsProducer.send("Scheduled message no ${counter.incrementAndGet()}")
    }
}
