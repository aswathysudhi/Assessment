package com.auth.service.controller;

import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth.service.model.ApiKeyValidationResponse;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;

@RestController
public class FederatedAuthController {
	@Value("${app.valid.api.key}")
	private String validApiKey;

	@Value("${app.jwt.expiration}")
	private long jwtExpirationMs;

	@Value("${app.jwt.secret.key}")
	private String jwtSecretKey;

	private SecretKey secretKey;

	@PostConstruct
	public void init() {
		byte[] keyBytes = jwtSecretKey.getBytes();
		this.secretKey = new SecretKeySpec(keyBytes, 0, keyBytes.length, "HmacSHA256");
	}

	@GetMapping("/validate")
	public ApiKeyValidationResponse validateApiKey(@RequestParam String apiKey) {
		// Validate API key
		if (!validApiKey.equals(apiKey)) {
			return new ApiKeyValidationResponse(false, null);
		}

		// If valid, issue internal token
		String internalToken = generateInternalToken();

		// Return validation response
		return new ApiKeyValidationResponse(true, internalToken);
	}

	private String generateInternalToken() {
		return Jwts.builder().setSubject("internaluser")
				.setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
				.signWith(secretKey, SignatureAlgorithm.HS256).compact();
	}

	public SecretKey getSecretKey() {
		return secretKey;
	}

}
