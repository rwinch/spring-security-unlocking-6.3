package org.example.messages;

import dev.openfga.sdk.api.client.OpenFgaClient;
import dev.openfga.sdk.api.client.model.ClientCheckRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MessageAuthz {

	final OpenFgaClient openFga;

	public MessageAuthz(OpenFgaClient openFga) {
		this.openFga = openFga;
	}

	public boolean check(MessageUser user, Optional<Message> message, String permission) {
		if (message.isEmpty()) {
			return true;
		}
		Message m = message.get();
		boolean isFromCurrentUser = checkRelation(user, m, "from");
		switch(permission) {
			case "write":
				return isFromCurrentUser;
			case "read":
				return isFromCurrentUser || checkRelation(user, m, "to");
			default:
				throw new IllegalArgumentException("Unknown permission: " + permission);
		}
	}

	private boolean checkRelation(MessageUser user, Message message, String relation) {
		ClientCheckRequest request = new ClientCheckRequest()
				.user(String.format("%s:%s", "user", user.getId()))
				.relation(relation)
				._object(String.format("%s:%s", "message", message.getId()));
		try {
			return this.openFga.check(request).get().getAllowed();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
