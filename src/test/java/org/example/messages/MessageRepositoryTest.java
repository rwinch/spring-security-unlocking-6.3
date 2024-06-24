package org.example.messages;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authorization.AuthorizationDeniedException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest
public class MessageRepositoryTest {
	@Test
	@WithRobUser
	void hello(@Autowired MessageRepository messages, @Autowired MessageUserRepository users) {
		MessageUser rob = users.findById(0L).get();
		MessageUser josh = users.findById(1L).get();
		assertThat(messages.findById(100L).get())
				.usingRecursiveComparison()
				.isEqualTo(new Message(100L, "Hi Josh", "This message is from Rob", rob, josh));
	}

	@Test
	@WithEveUser
	void eve(@Autowired MessageRepository messages) {
		assertThatExceptionOfType(AuthorizationDeniedException.class)
				.isThrownBy(() -> messages.findById(100L));
	}
}
