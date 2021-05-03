package pl.gizycki.sqsmigration.configuration

import com.amazonaws.services.sqs.AmazonSQSAsync
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class SqsConfig {

    @Bean
    fun queueMessagingTemplate(amazonSqs: AmazonSQSAsync) = QueueMessagingTemplate(amazonSqs)

}
