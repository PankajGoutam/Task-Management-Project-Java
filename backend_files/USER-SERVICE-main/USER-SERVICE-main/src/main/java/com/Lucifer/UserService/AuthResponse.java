package com.Lucifer.UserService;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
	private String jwt;
	private String message;
	private Boolean status;
}
