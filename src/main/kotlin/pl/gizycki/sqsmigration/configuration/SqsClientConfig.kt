package pl.gizycki.sqsmigration.configuration

import com.amazonaws.auth.AWSCredentialsProvider
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration
import com.amazonaws.services.sqs.AmazonSQSAsync
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@ConditionalOnProperty("sqs.endpoint")
class SqsClientConfig(
    @Value("\${cloud.aws.region.static}") private val awsRegion: String,
    @Value("\${sqs.endpoint}") private val sqsUrl: String
    ) {

    val logger = LoggerFactory.getLogger(this::class.java)

    @Bean
    fun amazonSqs(awsCredentialsProvider: AWSCredentialsProvider): AmazonSQSAsync? {
        logger.info("Configuring SQS to {} on region {}", sqsUrl, awsRegion)
        return AmazonSQSAsyncClientBuilder.standard()
            .withEndpointConfiguration(EndpointConfiguration(sqsUrl, awsRegion))
            .withCredentials(awsCredentialsProvider)
            .build()
    }

}
