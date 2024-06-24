package org.example.messages;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MessageAuthz {

	public boolean check(MessageUser user, Optional<Message> message, String permission) {
		if (message.isEmpty()) {
			return true;
		}
		Message m = message.get();
		boolean isFromCurrentUser = m.getFrom().getId() == user.getId();
		boolean isToCurrentUser = m.getTo().getId() == user.getId();
		switch(permission) {
			case "read":
				return isFromCurrentUser || isToCurrentUser;
			case "write":
				return isFromCurrentUser;
			default:
				throw new IllegalArgumentException("Unknown permission: " + permission);
		}
	}
}
