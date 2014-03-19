package main.java.com.mosby.utils;

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
