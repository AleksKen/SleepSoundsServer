package com.example.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AppApplication {
	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			runApplication<AppApplication>(*args)
		}
	}
}
