package org.example.messages;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MessagesApplicationTests {

	@Autowired
	MockMvc mockMvc;

	@Test
	@WithRobUser
	void accessInboxWhenAuthNThenOk() throws Exception {
		this.mockMvc.perform(get("/messages/inbox"))
				.andExpect(status().isOk());
	}

	@Test
	void accessInboxWhenNotAuthNThenUnAuthorized() throws Exception {
		this.mockMvc.perform(get("/messages/inbox"))
				.andExpect(status().isUnauthorized());
	}

}
