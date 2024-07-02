package com.auth.service.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @SuppressWarnings("deprecation")
	@Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
    	http
        .csrf().disable()
        .authorizeRequests(authorizeRequests ->
            authorizeRequests.anyRequest().authenticated()
        )
        .oauth2ResourceServer(oauth2ResourceServer ->
            oauth2ResourceServer.jwt())
        .formLogin().disable()
        .httpBasic().disable();
    return http.build();
       
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
            .username("user")
            .password("password")
            .roles("USER")
            .build();
        return new InMemoryUserDetailsManager(user);
    }}
