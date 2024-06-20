package org.example.messages;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class MessageUserDetailsService implements UserDetailsService {
	final MessageUserRepository users;

	public MessageUserDetailsService(MessageUserRepository users) {
		this.users = users;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MessageUser messageUser = users.findByEmail(username);
		if (messageUser == null) {
			throw new UsernameNotFoundException("Could not find user " + username);
		}
		return new MessageUserDetails(messageUser);
	}

	static final class MessageUserDetails extends MessageUser implements UserDetails {

		public MessageUserDetails(MessageUser user) {
			super(user);
		}

		@Override
		public String getUsername() {
			return getEmail();
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return AuthorityUtils.createAuthorityList("ROLE_USER");
		}

		@Override
		public boolean isEnabled() {
			return true;
		}

		@Override
		public boolean isAccountNonExpired() {
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}
	}
}
