package main.java.com.mosby.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.Date;

import main.java.com.mosby.model.annotations.validate.*;

public class ValidatorUtils<T> {

	private Class<T> type;
	private List<String> errors = new ArrayList<>();
	ResourceBundle labels;

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public void addErrors(String error) {
		this.errors.add(error);
	}

	public Class<T> getType() {
		return type;
	}

	public void setType(Class<T> type) {
		this.type = type;
	}

	public ValidatorUtils(Class<T> type, String loc) {
		this.type = type;
		Locale locale = new Locale(loc);
		labels = ResourceBundle.getBundle("main.java.com.mosby.i18n.errors", locale);
	}

	public T validate(T object) throws NoSuchMethodException,
			SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {

		Timestamp timestampStart = null;

		for (Field field : type.getDeclaredFields()) {

			if (field.isAnnotationPresent(NotNull.class)) {
				field.setAccessible(true);
				if (field.get(object) == null) {
					errors.add(labels.getString("PleaseEnterField")
							+ " \"" + labels.getString(field.getName())
							+ "\".");
					continue;
				}
			}

			if (field.isAnnotationPresent(Size.class)) {
				Size annotation = (Size) field.getAnnotation(Size.class);
				field.setAccessible(true);
				double value = 0;
				if (field.getType() == String.class) {
					if(field.get(object) != null){
					value = field.get(object).toString().length();
					} else {
						value = 0;
					}
				} else if (field.getType() == int.class
						|| field.getType() == double.class) {
					value = (double) field.get(object);
				}
				if (value < annotation.min()) {
					errors.add(labels.getString("TheSizeOfField")
							+ " \"" + labels.getString(field.getName())
							+ "\" " + labels.getString("mustBe") + " "
							+ labels.getString("atLeast") + " "
							+ annotation.min() + " "
							+ labels.getString("characters") + ".");
				}
				if (value > annotation.max()) {
					errors.add(labels.getString("TheSizeOfField")
							+ " \"" + labels.getString(field.getName())
							+ "\" " + labels.getString("mustBe") + " "
							+ labels.getString("noMore") + " "
							+ annotation.max() + " "
							+ labels.getString("characters") + ".");
				}
			}

			if (field.isAnnotationPresent(Min.class)) {
				Min annotation = (Min) field.getAnnotation(Min.class);
				field.setAccessible(true);
				double value = 0;

				if (field.getType() == String.class) {
					if(field.get(object) != null){
						value = field.get(object).toString().length();
						} else {
							value = 0;
						}
				} else if (field.getType() == int.class) {
					value = (int) field.get(object);
				} else if (field.getType() == double.class){
					value = (double) field.get(object);
				}
				if (value < annotation.value()) {
					errors.add(labels.getString("TheSizeOfField")
							+ " \"" + labels.getString(field.getName())
							+ "\" " + labels.getString("mustBe") + " "
							+ labels.getString("atLeast") + " "
							+ annotation.value() + " "
							+ labels.getString("characters") + ".");
				}

			}

			if (field.isAnnotationPresent(Max.class)) {
				Max annotation = (Max) field.getAnnotation(Max.class);
				field.setAccessible(true);
				double value = 0;
				if (field.getType() == String.class) {
					if(field.get(object) != null){
						value = field.get(object).toString().length();
						} else {
							value = 0;
						}
				} else if (field.getType() == int.class
						|| field.getType() == double.class) {
					value = (double) field.get(object);
				}
				if (value > annotation.value()) {
					errors.add(labels.getString("TheSizeOfField")
							+ " \"" + labels.getString(field.getName())
							+ "\" " + labels.getString("mustBe") + " "
							+ labels.getString("noMore") + " "
							+ annotation.value() + " "
							+ labels.getString("characters") + ".");
				}

			}

			if (field.isAnnotationPresent(Email.class)) {
				Email annotation = (Email) field.getAnnotation(Email.class);
				field.setAccessible(true);
				java.util.regex.Pattern pattern = java.util.regex.Pattern
						.compile(annotation.pattern());
				Matcher mathcer = pattern.matcher(field.get(object).toString());
				if (!mathcer.matches()) {
					errors.add(labels.getString("validateEmail"));
				}

			}
			if (field.isAnnotationPresent(Password.class)) {
				Password annotation = (Password) field
						.getAnnotation(Password.class);
				field.setAccessible(true);
				java.util.regex.Pattern pattern = java.util.regex.Pattern
						.compile(annotation.pattern());
				Matcher mathcer = pattern.matcher(field.get(object).toString());
				if (!mathcer.matches()) {
					errors.add(labels.getString("validatePassword"));
				}
			}
			if (field.isAnnotationPresent(StartFuture.class)) {
				field.setAccessible(true);
				timestampStart = (Timestamp) field.get(object);
				System.out.println(timestampStart);
				Date date = new Date();
				Timestamp timestampNow = new Timestamp(date.getTime());
				System.out.println(timestampNow);
				if (timestampStart != null
						&& timestampStart.before(timestampNow)) {
					errors.add(labels.getString("validateDataTime"));
				}

			}
			if (field.isAnnotationPresent(EndFuture.class)
					&& timestampStart != null) {
				field.setAccessible(true);
				Timestamp timestampEnd = (Timestamp) field.get(object);
				System.out.println(timestampStart);
				System.out.println(timestampEnd);
				if (timestampEnd.before(timestampStart)) {
					errors.add(labels.getString("validateStartEnd"));
				}
			}
		}

		if (errors.isEmpty()) {
			return object;
		} else {
			return null;
		}
	}

	public void checkConfirmPass(String pass, String confirmPass) {
		if (pass != null && confirmPass != null) {
			if (!pass.equals(confirmPass)) {
				errors.add(labels.getString("samePassword"));
			}
		} else {
			errors.add(labels.getString("confirmPassword"));
		}
	}
	
	public void correctFields(){
		errors.add(labels.getString("correctFields"));
	}
	
	public void changeEmail(){
		errors.add(labels.getString("changeEmail"));
	}
	
	public void changePassword(){
		errors.add(labels.getString("changePassword"));
	}
	
	public void inputNumber(){
		errors.add(labels.getString("inputNumber"));
	}
	}

}
