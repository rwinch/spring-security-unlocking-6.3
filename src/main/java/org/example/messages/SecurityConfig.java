package org.example.messages;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.method.PrePostTemplateDefaults;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration(proxyBeanMethods = false)
public class SecurityConfig {

	@Bean
	PrePostTemplateDefaults prePostTemplateDefaults() {
		return new PrePostTemplateDefaults();
	}

	@Bean
	DefaultSecurityFilterChain springSecurity(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests
				.anyRequest().authenticated()
			)
			.httpBasic(Customizer.withDefaults())
			.oauth2Login(Customizer.withDefaults())
			.logout(logout -> logout
				.permitAll()
			);
		return http.build();
	}

}
