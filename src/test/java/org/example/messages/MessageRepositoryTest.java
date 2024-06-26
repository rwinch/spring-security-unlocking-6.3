package org.example.messages;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.authorization.AuthorizationDeniedException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest
@Import(OpenFgaContainerConfiguration.class)
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
	@WithRobUser
	void saveWhenGranted(@Autowired MessageRepository messages, @Autowired MessageUserRepository users) {
		MessageUser rob = users.findById(0L).get();
		MessageUser eve = users.findById(2L).get();
		assertThat(messages.save(new Message(1000L, "Hi Eve", "This message is from Rob", rob, eve)));
	}

	@Test
	@WithEveUser
	void saveWhenDenied(@Autowired MessageRepository messages, @Autowired MessageUserRepository users) {
		MessageUser rob = users.findById(0L).get();
		MessageUser eve = users.findById(2L).get();
		assertThatExceptionOfType(AuthorizationDeniedException.class)
				.isThrownBy(() -> messages.save(new Message(1000L, "Hi Eve", "This message is from Rob", rob, eve)));
	}

	@Test
	@WithEveUser
	void eve(@Autowired MessageRepository messages) {
		assertThatExceptionOfType(AuthorizationDeniedException.class)
				.isThrownBy(() -> messages.findById(100L));
	}
}
