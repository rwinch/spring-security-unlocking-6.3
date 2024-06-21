package org.example.messages;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithMessageUser(id = 1L, email = "josh@example.com")
public @interface WithJoshUser {
}
