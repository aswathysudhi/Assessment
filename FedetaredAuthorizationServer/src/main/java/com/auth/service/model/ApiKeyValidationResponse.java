package com.auth.service.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiKeyValidationResponse {
	private boolean valid;
	private String internalToken;

	public ApiKeyValidationResponse() {
	}

	@JsonCreator
	public ApiKeyValidationResponse(@JsonProperty("valid") boolean valid,
			@JsonProperty("internalToken") String internalToken) {
		this.valid = valid;
		this.internalToken = internalToken;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getInternalToken() {
		return internalToken;
	}

	public void setInternalToken(String internalToken) {
		this.internalToken = internalToken;
	}

}
