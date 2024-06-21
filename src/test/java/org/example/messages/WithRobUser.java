package org.example.messages;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithMessageUser(id = 0L, email = "rob@example.com")
public @interface WithRobUser {
}
