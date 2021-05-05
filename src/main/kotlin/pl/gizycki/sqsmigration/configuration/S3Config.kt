package pl.gizycki.sqsmigration.configuration

import com.amazonaws.auth.AWSCredentialsProvider
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource


@Configuration
@ConditionalOnProperty("sqs.endpoint")
class S3Config{

    val logger = LoggerFactory.getLogger(this::class.java)

    @Bean
    fun amazonS3(
        @Value("\${cloud.aws.region.static}")  awsRegion: String,
        @Value("\${sqs.endpoint}")  s3Url: String,
        awsCredentialsProvider: AWSCredentialsProvider): AmazonS3 {
        logger.info("Configuring S3 to {} on region {}", s3Url, awsRegion)
        return AmazonS3ClientBuilder.standard()
            .withEndpointConfiguration(AwsClientBuilder.EndpointConfiguration("http://localhost:4566", "eu-west-1"))
            .withCredentials(awsCredentialsProvider)
            .enablePathStyleAccess()
            .build()
    }

}
