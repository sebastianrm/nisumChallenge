package com.srm.nisumChallenge.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Nisum Challenge
 * 
 * OnError Response
 * @author Sebastian Retamal Morales
 * @since 2023-12-15
 * @version 0.0.1-SNAPSHOT
 */
@Schema(description = "On Error messaje Object")
public class ErrorResponse {
	
	@Schema(description = "on Error messaje")
	private String message;
	
	
	public ErrorResponse(String message) {
		super();
		this.message = message;
	}

	public ErrorResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
