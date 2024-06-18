package org.example.messages;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MessageRepositoryTest {
	@Test
	void hello(@Autowired MessageRepository messages, @Autowired MessageUserRepository users) {
		MessageUser rob = users.findById(0L).get();
		MessageUser josh = users.findById(1L).get();
		assertThat(messages.findById(100L).get())
				.usingRecursiveComparison()
				.isEqualTo(new Message(100L, "Hi Josh", "This message is from Rob", rob, josh));
	}
}
