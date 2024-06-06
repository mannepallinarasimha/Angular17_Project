package com.ltimindtree.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {

	private String jwtToken;
	private String message;
}
