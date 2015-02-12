/**
 * 
 */
package com.yihu.myt.util;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author Administrator
 * 
 */
public class Encrypt {

	public static final String DESKEY = "nXZhl83vUQ4=";
	public static final byte BYTES_KEY[] = { -99, 118, 97, -105, -51, -17, 81,
			14 };

	public static String encrypt(String pwd) {
		
		return generateDigest(desEncrypt(pwd));
		
	}

	private static SecretKey loadKey(String algorithm, String keyFileName)
			throws Exception {
		SecretKey key = null;
		try {
			if (keyFileName == null) {
				byte signedData[] = BYTES_KEY;
				SecretKeySpec secrectKeySpec = new SecretKeySpec(signedData,
						algorithm);
				key = secrectKeySpec;
			} else {
				ObjectInputStream keyFile = new ObjectInputStream(
						new FileInputStream(keyFileName));
				key = (SecretKey) keyFile.readObject();
				keyFile.close();
			}
			if (key == null)
				throw new Exception("load secret key error.");
		} catch (Exception e) {
			// debug("load secret key error.", e);
			throw e;
		}
		return key;
	}

	private static String desEncrypt(String plaintext) {
		try {

			String ciphertext;

			String spec = "DES/ECB/PKCS5Padding";
			SecretKey key = loadKey("DES", null);
			Cipher cipher = Cipher.getInstance(spec);
			cipher.init(1, key);

			byte b[] = plaintext.getBytes("UTF8");
			byte cipherByteArray[] = cipher.doFinal(b);
			BASE64Encoder encoder = new BASE64Encoder();
			ciphertext = encoder.encode(cipherByteArray);
			return ciphertext;
		} catch (Exception e) {
			return null;
		}
	}

	private static String generateDigest(String password) {
		try {
			byte digest[];
			BASE64Encoder encoder = new BASE64Encoder();
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes("UTF8"));
			digest = md.digest();

			return encoder.encode(digest);
		} catch (Exception e) {
			return password;

		}
	}

}
