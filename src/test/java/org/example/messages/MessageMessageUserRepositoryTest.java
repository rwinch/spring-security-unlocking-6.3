package org.example.messages;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class MessageMessageUserRepositoryTest {
	@Test
	void rob(@Autowired MessageUserRepository users) {
		assertThat(users.findById(0L).get())
				.usingRecursiveComparison()
				.isEqualTo(new MessageUser(0L, "Rob", "Winch", "rob@example.com", "{bcrypt}$2a$10$J98R6KdtCssyA5wUb53C0OnEygupOAOJUec0ceNT7VewX3OPIP966"));
	}
}
