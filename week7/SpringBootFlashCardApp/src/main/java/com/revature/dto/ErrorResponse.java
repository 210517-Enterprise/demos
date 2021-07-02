package com.revature.dto;

import lombok.AllArgsConstructor;

// Data transfer object -> it's a blueprint for grabbing the information of something
// that's otherwise un-translatable into java (like an HTTP error)

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class ErrorResponse {
	
	private int status;
	private String message;
	private long timestamp; // System.getCurrentTimeMillis() -> converts to long

}
