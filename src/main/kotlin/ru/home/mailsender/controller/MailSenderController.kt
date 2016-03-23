package ru.home.mailsender.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.MultipartHttpServletRequest
import ru.home.mailsender.service.MailService

@Controller
@RequestMapping("mail")
class MailSenderController @Autowired constructor(val mailService: MailService) {

    data class Mail(var from: String, var to: Array<String>, var subject: String, var text: String)

    @RequestMapping("send", method = arrayOf(RequestMethod.POST))
    @ResponseBody
    fun send(@RequestParam("from") from: String,
             @RequestParam("to") to: Array<String>,
             @RequestParam("subject") subject: String,
             @RequestParam("text") text: String,
             request: MultipartHttpServletRequest): Map<String, MailService.Resp> {

        var files: MutableList<MultipartFile> = arrayListOf()
        request.fileNames.forEach {
            files.add(request.getFile(it))
        }

        return mailService.sendMail(Mail(from, to, subject, text), files)
    }
}