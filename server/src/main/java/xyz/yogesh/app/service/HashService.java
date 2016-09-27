package xyz.yogesh.app.service;

import org.mindrot.jbcrypt.BCrypt;

public class HashService {

	protected static String hash(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}
	
	protected static boolean check(String password, String hash) {
		return BCrypt.checkpw(password, hash);
	}
}
