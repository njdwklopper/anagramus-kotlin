package tech.klopper.anagramus

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AnagramusApplication

fun main(args: Array<String>) {
	runApplication<AnagramusApplication>(*args)
}
