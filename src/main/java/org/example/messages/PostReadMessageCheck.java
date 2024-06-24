package org.example.messages;

import org.springframework.security.access.prepost.PostAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@PostAuthorize("@messageAuthz.check(principal, returnObject, 'read')")
public @interface PostReadMessageCheck {
}