package com.sampark.grocery.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
 
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionUtil {
	private static SecretKeySpec secretKey;
    private static byte[] key;
    private static String sKey = "SAMPARK SOFTWARE PRIVATE LIMITED, GURGAON";
    
    public static void setKey(String myKey) {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
 
    public static String encrypt(String strToEncrypt) {
        try {
            setKey(sKey);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
 
    public static String decrypt(String strToDecrypt) {
        try {
            setKey(sKey);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        }
        catch (Exception e) {
            e.printStackTrace();
            
        }
        return null;
    }
    public static void main(String[] args) {
		String s = "12345";
		String s1 = "";
		
		s=new EncryptionUtil().encrypt(s);
		System.out.println("password is    "+s  );
		s1=new EncryptionUtil().decrypt(s);
		System.out.println(s+"    "+s1);
	}
}
