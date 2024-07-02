package com.auth.service.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
public class AuthorizationServerConfig { @SuppressWarnings({ "deprecation", "removal" })
@Bean
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeRequests(authorizeRequests ->
            authorizeRequests
                .requestMatchers("/uaa/**").authenticated()
                .anyRequest().permitAll()
        )
        .oauth2Login();

    return http.build();
}

@Bean
public ClientRegistrationRepository clientRegistrationRepository() {
    ClientRegistration registration = ClientRegistration.withRegistrationId("my-client")
            .clientId("my-client-id")
            .clientSecret("my-client-secret")
            .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
            .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
            .scope("read", "write")
            .authorizationUri("https://example.com/oauth/authorize")
            .tokenUri("https://example.com/oauth/token")
            .userInfoUri("https://example.com/user")
            .userNameAttributeName("id")
            .build();
    return new InMemoryClientRegistrationRepository(registration);
}}
 