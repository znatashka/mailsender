package ru.home.mailsender

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@RunWith(SpringJUnit4ClassRunner::class)
@SpringApplicationConfiguration(classes = arrayOf(MailsenderApplication::class))
class MailsenderApplicationTests {

	@Test
	fun contextLoads() {
	}

}
