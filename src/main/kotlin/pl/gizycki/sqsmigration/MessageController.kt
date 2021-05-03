package pl.gizycki.sqsmigration

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
@ConditionalOnProperty(prefix = "sqs", name = ["producer"])
class MessageController(val sqsProducer: SqsProducer) {

    @GetMapping("/{message}")
    fun sendMessage(@PathVariable message: String) {
        sqsProducer.send(message)
    }
}
