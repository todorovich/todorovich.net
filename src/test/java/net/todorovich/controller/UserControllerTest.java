package net.todorovich.controller;

import lombok.extern.slf4j.Slf4j;
import net.todorovich.server.ServerApplication;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import java.text.MessageFormat;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes=ServerApplication.class)
class UserControllerTest {
	@LocalServerPort
	private int port;

	@Autowired
	private UserController controller;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@DisplayName("Autowire UserController")
	void contextLoads()
	{
		Assertions.assertThat(controller).isNotNull();
	}

	@Test @DisplayName("Get User")
	void callUserController()
	{
		var response = restTemplate.postForEntity(
			MessageFormat.format("https://www.todorovich.local:{0}/users/micho", String.valueOf(port)),
			null,
			String.class
		);

		log.atInfo().log(response.getBody());
	}
}