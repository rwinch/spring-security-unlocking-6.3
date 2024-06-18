package org.example.messages;

import org.springframework.data.repository.CrudRepository;

public interface MessageUserRepository extends CrudRepository<MessageUser, Long> {
	MessageUser findByEmail(String email);
}
