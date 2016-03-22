package ru.home.mailsender.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSenderImpl

@Configuration
@ConfigurationProperties
open class AppConfig() {

    @Value("\${mail.host}")
    var host: String = ""
    @Value("\${mail.port}")
    var port: Int = 0
    @Value("\${mail.username}")
    var username: String = ""
    @Value("\${mail.password}")
    var password: String = ""
    @Value("\${mail.debug}")
    var isDebug: Boolean = false

    @Bean
    open fun javaMailSender(): JavaMailSenderImpl {
        var mailSender = JavaMailSenderImpl()

        mailSender.host = host
        mailSender.port = port
        mailSender.username = username
        mailSender.password = password

        var prop = mailSender.javaMailProperties;
        prop.put("mail.transport.protocol", "smtps")
        prop.put("mail.smtps.auth", "true")
        prop.put("mail.smtps.starttls.enable", "true")
        prop.put("mail.debug", isDebug)

        return mailSender;
    }
}