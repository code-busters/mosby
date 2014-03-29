package main.java.com.mosby.utils;

import org.apache.log4j.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public class EncryptionUtils {
    private static Logger log = Logger.getLogger(EncryptionUtils.class);

    private static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";

    private static final int SALT_BYTE_SIZE = 24;
    private static final int HASH_BYTE_SIZE = 24;
    private static final int PBKDF2_ITERATIONS = 1200;

    private static final int ITERATION_INDEX = 0;
    private static final int SALT_INDEX = 1;
    private static final int PBKDF2_INDEX = 2;

    private static final char[] HEX = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    public static String createHash(String password) {
        return createHash(password.toCharArray());
    }

    public static String createHash(char[] password) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_BYTE_SIZE];
        random.nextBytes(salt);

        byte[] hash = new byte[0];
        try {
            hash = pbkdf2(password, salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            log.error(e);
        }
        return main.java.com.mosby.utils.StringUtils.concat(PBKDF2_ITERATIONS, ":", toHex(salt), ":", toHex(hash));
    }

    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
        return keyFactory.generateSecret(spec).getEncoded();
    }

    public static String toHex(byte[] array) {
        BigInteger bigInteger = new BigInteger(1, array);
        String hex = bigInteger.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0) {
            return String.format("%0" + paddingLength + "d", 0) + hex;
        } else {
            return hex;
        }
    }

    public static boolean validatePassword(String password, String correctHash) {
        return validatePassword(password.toCharArray(), correctHash);
    }

    public static boolean validatePassword(char[] password, String correctHash) {
        String[] params = correctHash.split(":");
        int iterations = Integer.parseInt(params[ITERATION_INDEX]);
        byte[] salt = fromHex(params[SALT_INDEX]);
        byte[] hash = fromHex(params[PBKDF2_INDEX]);
        byte[] testHash = new byte[0];
        try {
            testHash = pbkdf2(password, salt, iterations, hash.length);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            log.error(e);
        }
        return slowEquals(hash, testHash);
    }

    private static byte[] fromHex(String hex) {
        byte[] binary = new byte[hex.length() / 2];
        for (int i = 0; i < binary.length; i++) {
            binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return binary;
    }

    private static boolean slowEquals(byte[] a, byte[] b) {
        int diff = a.length ^ b.length;
        for (int i = 0; i < a.length && i < b.length; i++) {
            diff |= a[i] ^ b[i];
        }
        return diff == 0;
    }

    public static String getStringFromSHA256(String stringToEncrypt) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            log.error(e);
        }
        messageDigest.update(stringToEncrypt.getBytes());
        return byteArray2Hex(messageDigest.digest());
    }

    public static String byteArray2Hex(byte[] bytes) {
        StringBuffer sb = new StringBuffer(bytes.length * 2);
        for(final byte b : bytes) {
            sb.append(HEX[(b & 0xF0) >> 4]);
            sb.append(HEX[b & 0x0F]);
        }
        return sb.toString();
    }
}
