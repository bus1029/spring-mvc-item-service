package hello.springmvc.itemservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringMvcItemServiceApplication

fun main(args: Array<String>) {
	runApplication<SpringMvcItemServiceApplication>(*args)
}
