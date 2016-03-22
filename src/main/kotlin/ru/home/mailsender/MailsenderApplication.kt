package ru.home.mailsender

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class MailsenderApplication

fun main(args: Array<String>) {
    SpringApplication.run(MailsenderApplication::class.java, *args)
}