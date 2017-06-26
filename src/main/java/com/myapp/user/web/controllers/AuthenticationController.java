package com.myapp.user.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.util.NumberUtils;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.user.dao.UserDao;
import com.myapp.user.entity.User;
import com.myapp.user.models.AccountCredentials;
import com.myapp.user.service.AuthenticationService;

@RestController
@RequestMapping("/")
public class AuthenticationController {

	@Autowired
	@Qualifier("userDao")
	UserDao userDao;

	@Autowired
	@Qualifier("authenticationService")
	AuthenticationService authenticationService;

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String login(@RequestBody AccountCredentials cred) {

		String userId = "";
		if (null != cred) {
			userId = cred.getUsername();
		}
		User user = userDao.getUserByUserId(NumberUtils.parseNumber(userId, Long.class));
		String authToken = "";
		if (null != user) {
			authToken = authenticationService.getAuthToken(Long.toString(user.getUserId()));
			System.out.println("Authtoken : " + authToken);
		}
		return authToken;
	}
}