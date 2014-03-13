package test.java.main.java.com.mosby.utils;

import main.java.com.mosby.utils.EncryptionUtils;

import org.junit.Assert;
import org.junit.Test;

public class EncryptionUtilsTest {
    @Test
    public void testCreateHash() {
        String firsthash = EncryptionUtils.createHash("pass");
        String secondHash = EncryptionUtils.createHash("pass");
        Assert.assertNotSame(firsthash, secondHash);

        Assert.assertTrue(EncryptionUtils.validatePassword("pass", firsthash));
        Assert.assertTrue(EncryptionUtils.validatePassword("pass", secondHash));
    }

    @Test
    public void testValidatePassword() {
        Assert.assertTrue(EncryptionUtils.validatePassword("pass", EncryptionUtils.createHash("pass")));
    }
}
