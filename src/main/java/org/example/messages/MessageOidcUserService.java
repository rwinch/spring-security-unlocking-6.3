package org.example.messages;

import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;

@Service
public class MessageOidcUserService implements OAuth2UserService<OidcUserRequest, OidcUser> {
	private OAuth2UserService<OidcUserRequest, OidcUser> delegate = new OidcUserService();

	private final MessageUserRepository users;

	public MessageOidcUserService(MessageUserRepository users) {
		this.users = users;
	}

	@Override
	public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
		OidcUser oidcUser = this.delegate.loadUser(userRequest);
		MessageOidcUser result = new MessageOidcUser(oidcUser);
		MessageUser user = users.findByEmail(oidcUser.getName());
		result.setEmail(user.getEmail());
		result.setId(user.getId());
		result.setFirstName(user.getFirstName());
		result.setLastName(user.getLastName());
		return result;
	}

	static class MessageOidcUser extends MessageUser implements OidcUser {
		private final OidcUser delegate;

		MessageOidcUser(OidcUser delegate) {
			this.delegate = delegate;
		}

		@Override
		public Map<String, Object> getClaims() {
			return delegate.getClaims();
		}

		@Override
		public OidcUserInfo getUserInfo() {
			return delegate.getUserInfo();
		}

		@Override
		public OidcIdToken getIdToken() {
			return delegate.getIdToken();
		}

		@Override
		@Nullable
		public <A> A getAttribute(String name) {
			return delegate.getAttribute(name);
		}

		@Override
		public Map<String, Object> getAttributes() {
			return delegate.getAttributes();
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return delegate.getAuthorities();
		}

		@Override
		public String getName() {
			return delegate.getName();
		}
	}
}
