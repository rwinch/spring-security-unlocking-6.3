package org.example.messages;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

interface MessageRepository extends CrudRepository<Message, Long> {

	@Override
	@PostReadMessageCheck
	Optional<Message> findById(Long aLong);

	@Override
	@PreWriteMessageCheck(message = "#entity")
	<S extends Message> S save(S entity);

	Iterable<Message> findByFromId(Long id);

	Iterable<Message> findByToId(Long id);
}
