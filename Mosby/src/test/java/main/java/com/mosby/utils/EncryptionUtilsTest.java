package test.java.main.java.com.mosby.utils;

import main.java.com.mosby.utils.EncryptionUtils;

import org.junit.Assert;
import org.junit.Test;

public class EncryptionUtilsTest {
    @Test
    public void testCreateHash() {
        String pass1 = EncryptionUtils.createHash("root");
        String pass2 = EncryptionUtils.createHash("root");
        System.out.println(EncryptionUtils.createHash("root"));

        Assert.assertNotEquals(pass1, pass1);

        Assert.assertTrue(EncryptionUtils.validatePassword("root", pass1));

    }
}
