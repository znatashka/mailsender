package ru.home.mailsender.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.MailException
import org.springframework.mail.javamail.JavaMailSenderImpl
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import ru.home.mailsender.controller.MailSenderController
import java.util.*

@Component
class MailService @Autowired constructor(val mailSender: JavaMailSenderImpl) {

    interface Resp {
        var code: Int

        data class Success(override var code: Int) : Resp
        data class Error(override var code: Int, var msg: String?) : Resp
    }

    fun sendMail(mail: MailSenderController.Mail, files: MutableList<MultipartFile>): Map<String, Resp> {
        var map = HashMap<String, Resp>()
        mail.to.forEach {
            var message = mailSender.createMimeMessage()

            var helper = MimeMessageHelper(message, true)
            helper.setFrom(mail.from, mail.name);
            helper.setTo(it);
            helper.setSubject(mail.subject);
            helper.setText(mail.text);

            files.forEach {
                helper.addAttachment(it.originalFilename, it)
            }

            try {
                mailSender.send(message)
                map.put(it, Resp.Success(0))
            } catch(e: MailException) {
                e.printStackTrace()
                map.put(it, Resp.Error(-1, e.message))
            }
        }
        return map
    }
}