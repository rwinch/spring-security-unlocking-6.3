package org.example.messages;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MessagesApplication {

	@Bean
	CommandLineRunner init(MessageRepository messages) {
		return args -> {
//			messages.save(new Message(1L, "Hi"));
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(MessagesApplication.class, args);
	}

}
