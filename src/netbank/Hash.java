package netbank;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Hash {

	public static String SHA512(String passwordToHash, String salt) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-512");
			md.update(salt.getBytes());
			byte[] bytes = md.digest(passwordToHash.getBytes());
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}

	}

	public static String getSalt() {
		SecureRandom r = new SecureRandom();
		StringBuffer sb = new StringBuffer();
		while (sb.length() < 32) {
			sb.append(Integer.toHexString(r.nextInt()));
		}

		return sb.toString().substring(0, 32);
	}
}
