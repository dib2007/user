package com.myapp.user.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("securityUtil")
public class SecurityUtil {

	@Autowired
	@Qualifier("genericUtil")
	GenericUtil genericUtil;

	private static final int ITERATIONS = 1000;

	public byte[] getSalt() throws NoSuchAlgorithmException {
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		return salt;
	}

	public String generatePasswordHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		char[] chars = password.toCharArray();
		byte[] salt = getSalt();

		PBEKeySpec spec = new PBEKeySpec(chars, salt, ITERATIONS, 64 * 8);
		SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		byte[] hash = skf.generateSecret(spec).getEncoded();
		return genericUtil.toHex(salt) + ":" + genericUtil.toHex(hash);
	}

	public boolean validate(String originalPassword, String storedPassword, String storedSalt)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		byte[] salt = genericUtil.fromHex(storedSalt);
		byte[] hash = genericUtil.fromHex(storedPassword);

		PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, ITERATIONS, hash.length * 8);
		SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		byte[] testHash = skf.generateSecret(spec).getEncoded();

		int diff = hash.length ^ testHash.length;
		for (int i = 0; i < hash.length && i < testHash.length; i++) {
			diff |= hash[i] ^ testHash[i];
		}
		return diff == 0;
	}
}
