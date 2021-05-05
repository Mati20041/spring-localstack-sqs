package pl.gizycki.sqsmigration.configuration

import com.spring.loader.S3PropertiesLocation
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@S3PropertiesLocation("my-bucket/sample.properties")
@Configuration
class S3ConfigTest {
    @Bean
    fun afterPropertiesSet(@Value("\${test}") checkingVal: String): Object {
        LoggerFactory.getLogger(this::class.java).info("LOADED PROPERTY test={}", checkingVal)
        return Object()
    }

}
