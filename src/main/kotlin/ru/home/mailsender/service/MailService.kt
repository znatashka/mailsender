package ru.home.mailsender.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.FileSystemResource
import org.springframework.mail.MailException
import org.springframework.mail.javamail.JavaMailSenderImpl
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Component
import ru.home.mailsender.rest.MailSenderController
import java.io.File
import java.util.*

@Component
class MailService @Autowired constructor(val mailSender: JavaMailSenderImpl) {

    @Value("\${file.path}")
    var filePath = ""

    interface Resp {
        class Success() : Resp
        data class Error(var msg: String?) : Resp
    }

    fun sendMail(mail: MailSenderController.Mail): MutableMap<String, Resp> {
        var map = HashMap<String, Resp>()
        mail.to.forEach {
            var message = mailSender.createMimeMessage()

            var helper = MimeMessageHelper(message, true)
            helper.setFrom(mail.from);
            helper.setTo(it);
            helper.setSubject(mail.subject);
            helper.setText(mail.text);

            var file = FileSystemResource(File(filePath))
            helper.addAttachment(file.filename, file)

            try {
                mailSender.send(message)
                map.put(it, Resp.Success())
            } catch(e: MailException) {
                map.put(it, Resp.Error(e.message))
            }
        }
        return map
    }
}