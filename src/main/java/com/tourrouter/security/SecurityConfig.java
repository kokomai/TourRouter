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
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf((csrf)->csrf.disable())
			.authorizeHttpRequests(
					(authz) -> 
						authz
						.requestMatchers("/member/createMember").permitAll()
						.requestMatchers("/member/getAllMember").permitAll()
						.anyRequest().authenticated()
						
			);
		return http.build();
	}
}
