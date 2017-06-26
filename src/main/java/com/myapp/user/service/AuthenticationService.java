package com.myapp.user.service;

import java.util.Base64;

import org.springframework.stereotype.Service;

@Service("authenticationService")
public class AuthenticationService {

	public String getAuthToken(String userName){
		return Base64.getEncoder().encodeToString(userName.getBytes());
	}
}
