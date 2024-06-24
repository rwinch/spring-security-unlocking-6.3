package org.example.messages;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PostAuthorize;

import java.util.Optional;

interface MessageRepository extends CrudRepository<Message, Long> {

	@Override
	@PostAuthorize("@messageAuthz.check(principal, returnObject, 'read')")
	Optional<Message> findById(Long aLong);

	Iterable<Message> findByFromId(Long id);

	Iterable<Message> findByToId(Long id);
}
