package swe4.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtil {
  private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

  public static String generateHash(String password) {
    MessageDigest hashFunction = null;
    try {
      hashFunction = MessageDigest.getInstance("MD5");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    hashFunction.reset();
    byte[] hashedPassword = hashFunction.digest(password.getBytes());
    return bytesToString(hashedPassword);
  }

  public static boolean isValid(String password, String passwordHash) {
    if (generateHash(password).equals(passwordHash)) {
      return true;
    } else {
      return false;
    }
  }

  private static String bytesToString(byte[] bytes) {
    char[] hexChars = new char[bytes.length * 2];
    for (int i = 0; i < bytes.length; ++i) {
      int v = bytes[i] & 0xFF;
      hexChars[i * 2] = hexArray[v >>> 4];
      hexChars[i * 2 + 1] = hexArray[v & 0x0F];
    }
    return new String(hexChars);
  }
}
