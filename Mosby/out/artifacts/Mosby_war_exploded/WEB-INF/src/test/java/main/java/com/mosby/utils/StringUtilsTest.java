package main.java.com.mosby.utils;

import main.java.com.mosby.utils.StringUtils;

import org.junit.Assert;
import org.junit.Test;

public class StringUtilsTest {
    @Test
    public void testConcat() {
        Assert.assertEquals("test" + " " + 123, StringUtils.concat("test", " ", 123));
    }
}
