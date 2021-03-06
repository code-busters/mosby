package test.java.main.java.com.mosby.utils;

import main.java.com.mosby.utils.EncryptionUtils;

import org.junit.Assert;
import org.junit.Test;

public class EncryptionUtilsTest {
        @Test
        public void testCreateHash() {
            String pass1 = EncryptionUtils.createHash("root");
            String pass2 = EncryptionUtils.createHash("root");

            Assert.assertTrue(EncryptionUtils.validatePassword("root", pass1));

        }

        @Test
        public void testSecureCode() {
            String pass1 = EncryptionUtils.createHash("root",2);
            String pass2 = EncryptionUtils.createHash("root",2);

            Assert.assertTrue(EncryptionUtils.validatePassword("root,2", pass1));

        }
}
