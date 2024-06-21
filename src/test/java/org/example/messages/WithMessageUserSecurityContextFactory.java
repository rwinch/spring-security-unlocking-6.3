package org.example.messages;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

public class WithMessageUserSecurityContextFactory implements WithSecurityContextFactory<WithMessageUser> {
	@Override
	public SecurityContext createSecurityContext(WithMessageUser user) {
		MessageUser result = new MessageUser(user.id(), "Rob", "Winch", user.email(), "{bcrypt}$2a$10$J98R6KdtCssyA5wUb53C0OnEygupOAOJUec0ceNT7VewX3OPIP966");
		MessageUserDetailsService.MessageUserDetails principal = new MessageUserDetailsService.MessageUserDetails(result);
		Authentication authentication = new UsernamePasswordAuthenticationToken(principal, principal.getPassword(), principal.getAuthorities());
		return new SecurityContextImpl(authentication);
	}
}
