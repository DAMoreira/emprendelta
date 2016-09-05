package org.utn.frd.lsi.service;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Logger;

import org.utn.frd.lsi.util.PasswordHash;

public class SecurityService {
	
	private static final Logger log = Logger.getLogger(SecurityService.class.getName());
	
	public static String generateActivationKey() {
		return new BigInteger(130, (new SecureRandom())).toString(32);
	}
	
	public static String generateRecoverPasswordCode() {
		return new BigInteger(130, (new SecureRandom())).toString(10);
	}
	
	public static String encrypt(String noEncryptedString) throws NoSuchAlgorithmException, InvalidKeySpecException {
		return PasswordHash.createHash( noEncryptedString );
	}

}
