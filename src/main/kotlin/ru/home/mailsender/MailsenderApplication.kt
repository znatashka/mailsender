package ru.home.mailsender

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.context.web.SpringBootServletInitializer
import java.awt.Desktop
import java.net.URI

@SpringBootApplication
open class MailsenderApplication : SpringBootServletInitializer() {

    override fun configure(builder: SpringApplicationBuilder?): SpringApplicationBuilder? {
        return builder?.sources(MailsenderApplication::class.java)
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(MailsenderApplication::class.java, *args)
    launchBrowser()
}

fun launchBrowser() {
    val url = "http://localhost:8080/";

    if (Desktop.isDesktopSupported()) {
        Desktop.getDesktop().browse(URI(url));
    } else {
        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url)
    }
}