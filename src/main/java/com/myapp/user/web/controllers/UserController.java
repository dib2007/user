package com.myapp.user.web.controllers;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.util.NumberUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.user.dao.UserDao;
import com.myapp.user.entity.User;
import com.myapp.user.models.UserView;
import com.myapp.user.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	@Qualifier("userDao")
	UserDao userDao;

	@Autowired
	@Qualifier("userService")
	UserService userService;

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String createUser(@RequestBody UserView user) {
		long id = 0;
		try {
			id = userService.createUser(user);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		if (id > 0) {
			return "CREATED";
		} else {
			return "NOT CREATED";
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<User> getAllUsers() {
		return new ArrayList<User>();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String updateUser(@PathVariable("id") String id) {
		return "UPDATED";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User getUser(@PathVariable("id") String id) {
		User user = userDao.getUserByUserId(NumberUtils.parseNumber(id, Long.class));
		return user;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String deleteUser(@PathVariable("id") String id) {
		return "DELETED";
	}
}
