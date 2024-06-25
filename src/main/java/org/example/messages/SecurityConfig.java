package org.example.messages;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.WebauthnConfigurer;
import org.springframework.security.web.DefaultSecurityFilterChain;

@EnableWebSecurity
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
			.formLogin(Customizer.withDefaults());
		return http.build();
	}
}