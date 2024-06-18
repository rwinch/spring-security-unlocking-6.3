package org.example.messages;

import org.springframework.data.repository.CrudRepository;

interface MessageRepository extends CrudRepository<Message, Long> {

	Iterable<Message> findByFromId(Long id);

	Iterable<Message> findByToId(Long id);
}
