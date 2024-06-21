package org.example.messages;

import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithMessageUserSecurityContextFactory.class)
public @interface WithMessageUser {
	long id();
	String email();
}
