package org.example.messages;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.experimental.boot.server.exec.CommonsExecWebServerFactoryBean;
import org.springframework.experimental.boot.test.context.EnableDynamicProperty;
import org.springframework.experimental.boot.test.context.OAuth2ClientProviderIssuerUri;

import static org.springframework.experimental.boot.server.exec.MavenClasspathEntry.springBootStarter;

@TestConfiguration(proxyBeanMethods = false)
@EnableDynamicProperty
public class AuthzConfiguration {
	@Bean
	@OAuth2ClientProviderIssuerUri
	static CommonsExecWebServerFactoryBean authorizationServer() {
		// @formatter:off
		return CommonsExecWebServerFactoryBean.builder()
				// ...
				.defaultSpringBootApplicationMain()
				.classpath((classpath) -> classpath
						// Add spring-boot-starter-authorization-server & transitive dependencies
						.entries(springBootStarter("oauth2-authorization-server"))
				);
		// @formatter:on
	}
}
