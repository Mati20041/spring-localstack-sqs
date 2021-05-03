package pl.gizycki.sqsmigration

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener
import org.springframework.stereotype.Component

@Component
@ConditionalOnProperty(prefix = "sqs", name = ["listener"])
class SqsListener {
    val logger = LoggerFactory.getLogger(this::class.java)

    @SqsListener("\${sqs.queueName}")
    fun handle(msg: String) {
        logger.info("Received sqs message '{}'", msg)
    }

}

@Component
@ConditionalOnProperty(prefix = "sqs", name = ["producer"])
class SqsProducer(val queueMessagingTemplate: QueueMessagingTemplate, @Value("\${sqs.queueName}") val queueName: String) {
    val logger = LoggerFactory.getLogger(this::class.java)

    fun send(msg: String) {
        logger.info("Sending sqs message '{}'", msg)
        queueMessagingTemplate.convertAndSend(queueName, msg)
    }
}
