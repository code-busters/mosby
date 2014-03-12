package main.java.com.mosby.model.annotations.validate;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Password {

	String pattern();
}
