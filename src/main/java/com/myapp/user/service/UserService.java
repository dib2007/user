package com.myapp.user.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.myapp.user.dao.UserDao;
import com.myapp.user.entity.User;
import com.myapp.user.models.UserView;
import com.myapp.user.util.SecurityUtil;

@Service("userService")
public class UserService {

	@Autowired
	@Qualifier("securityUtil")
	SecurityUtil securityUtil;

	@Autowired
	@Qualifier("userDao")
	UserDao userDao;
	public long createUser(UserView userView) throws NoSuchAlgorithmException, InvalidKeySpecException {
		User user = new User();
		user.setUserDisplayName(userView.getDisplayName());
		user.setUserEmail(userView.getEmail());
		user.setUserFName(userView.getfName());
		user.setUserLName(userView.getlName());
		user.setUserMName(userView.getmName());
		String passWd = userView.getPassWd();
		String hash = securityUtil.generatePasswordHash(passWd);
		String[] hashes = hash.split(":");
		if (null != hashes && hashes.length == 2) {
			user.setUserPassword(hashes[1]);
			user.setUserSalt(hashes[0]);
		}
		User savedUser = userDao.save(user);
		if(null != savedUser){
			return savedUser.getUserId();
		}else{
			return 0;
		}
	}
}
