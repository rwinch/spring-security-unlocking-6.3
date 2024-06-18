package org.example.messages;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
	final MessageUserRepository users;

	public UserController(MessageUserRepository users) {
		this.users = users;
	}

	@GetMapping
	Iterable<MessageUser> users() {
		return this.users.findAll();
	}
}
