package pl.gizycki.sqsmigration.configuration

import com.amazonaws.auth.AWSCredentialsProvider
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder
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

    @Bean
    fun amazonSqs(awsCredentialsProvider: AWSCredentialsProvider) =
        AmazonSQSAsyncClientBuilder.standard()
            .withEndpointConfiguration(EndpointConfiguration(sqsUrl, awsRegion))
            .withCredentials(awsCredentialsProvider)
            .build()

}
