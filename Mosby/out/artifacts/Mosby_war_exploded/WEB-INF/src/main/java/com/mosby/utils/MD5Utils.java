package main.java.com.mosby.utils;

import main.java.com.mosby.web.servlets.api.ApiServlet;

import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.Logger;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
	
	private static Logger logger = Logger.getLogger(MD5Utils.class);
	
    public static String getMD5String(String text) {
        String result = "";
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(text.getBytes(Charset.forName("UTF8")));
            final byte[] resultByte = messageDigest.digest();
            result = new String(Hex.encodeHex(resultByte));
        } catch (NoSuchAlgorithmException e) {
        	logger.error(e);
        }

        return result;
    }
}
