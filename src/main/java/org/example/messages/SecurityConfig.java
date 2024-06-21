package org.example.messages;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration(proxyBeanMethods = false)
public class SecurityConfig {

	@Bean
	DefaultSecurityFilterChain springSecurity(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests
				.anyRequest().authenticated()
			)
			.httpBasic(Customizer.withDefaults())
			.with(new WebauthnConfigurer<>(), (passkeys) -> passkeys
				.rpName("Spring Security Relying Party")
				.rpId("example.localhost")
				.allowedOrigins("https://example.localhost:8443")
			)
			.formLogin(form -> form
				.loginPage("/login")
				.permitAll()
			)
			.logout(logout -> logout
				.permitAll()
			);
		return http.build();
	}

}
