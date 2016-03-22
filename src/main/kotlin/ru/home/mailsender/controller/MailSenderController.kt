package ru.home.mailsender.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import ru.home.mailsender.service.MailService

@Controller
@RequestMapping("mail")
class MailSenderController @Autowired constructor(val mailService: MailService) {

    class Mail(var from: String, var to: Array<String>, var subject: String, var text: String)

    @RequestMapping("send", method = arrayOf(RequestMethod.POST))
    @ResponseBody
    fun send(@RequestBody mail: Mail): Map<String, MailService.Resp> {
        return mailService.sendMail(mail)
    }
}