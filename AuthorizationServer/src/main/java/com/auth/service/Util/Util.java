package com.auth.service.Util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.auth.service.model.ApiKeyValidationResponse;

@Component
public class Util {
	private final RestTemplate restTemplate;

	public Util(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Value("${app.jwt.secret}")
	private String jwtSecret;

	@Value("${app.jwt.expiration}")
	private long jwtExpirationMs;

	@Value("${apikey.auth.provider.url}")
	private String apiKeyAuthProviderUrl;

	public ApiKeyValidationResponse validateApiKey(String apiKey) {
		// invoke federated auth service api to validate token
		ResponseEntity<ApiKeyValidationResponse> response = restTemplate.exchange(
				apiKeyAuthProviderUrl + "?apiKey=" + apiKey, HttpMethod.GET, null, ApiKeyValidationResponse.class);

		return response.getBody();
	}

}
