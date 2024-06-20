package org.example.messages;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Controller
@RequestMapping("/messages/")
class MessageController {

	final MessageRepository messages;

	MessageController(MessageRepository messages) {
		this.messages = messages;
	}

	@GetMapping
	String index() {
		return "redirect:inbox";
	}

	@GetMapping("inbox")
	String inbox(@AuthenticationPrincipal MessageUser user, Map<String, Object> model) {
		model.put("messages", this.messages.findByToId(user.getId()));
		return "messages/inbox";
	}

	@GetMapping("sent")
	String sent(@AuthenticationPrincipal MessageUser user, Map<String, Object> model) {
		model.put("messages", this.messages.findByFromId(user.getId()));
		return "messages/sent";
	}

	@GetMapping("{id}")
	String view(@PathVariable Long id, Map<String, Object> model) {
		model.put("message", this.messages.findById(id).get());
		return "messages/view";
	}
}
