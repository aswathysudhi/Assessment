package com.auth.service.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.service.Util.Util;
import com.auth.service.data.TokenResponse;
import com.auth.service.model.ApiKeyValidationResponse;

@RestController
public class AuthController {
	@Autowired
	Util util;

	@Value("${app.valid.api.key}")
	private String apiKey;

	@GetMapping("/token")
	public ResponseEntity<TokenResponse> getToken() {

		ApiKeyValidationResponse response = util.validateApiKey(apiKey);

		// Validate API key with apikey-auth-provider
		if (!response.isValid()) {
			return ResponseEntity.status(401).build();
		}

		String jwtToken = response.getInternalToken();

		// Return JWT token to client
		return ResponseEntity.ok(new TokenResponse(jwtToken));

	}

}
