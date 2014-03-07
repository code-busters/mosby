package main.java.com.mosby.utils;

public class StringUtils {
    public static String concat(Object... values) {
        StringBuilder output = new StringBuilder();
        for (Object object : values) {
            output.append(object);
        }
        return output.toString();
    }
}