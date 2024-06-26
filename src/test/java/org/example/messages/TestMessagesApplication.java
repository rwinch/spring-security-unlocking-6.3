package org.example.messages;

import org.springframework.boot.SpringApplication;

public class TestMessagesApplication {

	public static void main(String[] args) {
		SpringApplication
				.from(MessagesApplication::main)
				.with(OpenFgaContainerConfiguration.class)
				.run(args);
	}
}
