package ru.home.mailsender.rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.home.mailsender.service.MailService
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Component
@Path("mail")
class MailSenderController @Autowired constructor(val mailService: MailService) {

    class Mail(var from: String, var to: Array<String>, var subject: String, var text: String)

    @POST
    @Path("send")
    @Produces(MediaType.APPLICATION_JSON)
    fun send(mail: Mail): Response {
        val resp = mailService.sendMail(mail)
        return Response.ok(resp).build()
    }
}