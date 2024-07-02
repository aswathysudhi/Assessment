package com.auth.service.model;

public class ApiKeyValidationResponse {
	 private boolean valid;

	    public ApiKeyValidationResponse(boolean valid) {
	        this.valid = valid;
	    }

	    public boolean isValid() {
	        return valid;
	    }
}
