package pl.gizycki.sqsmigration

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SqsMigrationApplication

fun main(args: Array<String>) {
	runApplication<SqsMigrationApplication>(*args)
}
