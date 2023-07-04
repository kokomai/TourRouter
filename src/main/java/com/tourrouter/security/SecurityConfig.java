package com.tourrouter.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	private static final String[] API_AUTH_WHITELIST = {
			"/member/createMember",
			"/member/getAllMember"
	};
	
	private static final String[] WEB_AUTH_WHITELIST = {
			"/docs/**",
			"/index.html"
	};
	
	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		return (web)-> web.ignoring()
				.requestMatchers(WEB_AUTH_WHITELIST);
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf((csrf)->csrf.disable())
			.authorizeHttpRequests(
					(authz) -> 
						authz
						.requestMatchers(API_AUTH_WHITELIST).permitAll()
						.anyRequest().authenticated()
						
			);
		return http.build();
	}
}
