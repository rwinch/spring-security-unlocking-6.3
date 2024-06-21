package org.example.messages;

import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithMessageUser(id = 2L, email = "eve@example.com")
public @interface WithEveUser {
}
