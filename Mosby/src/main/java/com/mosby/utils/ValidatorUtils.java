package main.java.com.mosby.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.Date;

import main.java.com.mosby.model.annotations.validate.*;

public class ValidatorUtils<T> {

	private Class<T> type;
	private List<String> errors = new ArrayList<>();

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

	public ValidatorUtils(Class<T> type) {
		this.type = type;
	}

	public T validate(T object) throws NoSuchMethodException,
			SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		System.out.println("dsad");
		Timestamp timestampStart = null;
		for (Field field : type.getDeclaredFields()) {
			
			if (field.isAnnotationPresent(NotNull.class)) {
				field.setAccessible(true);
				if (field.get(object) == null) {
					errors.add(field.getName() + " must don`t be null");
					continue;
				}
			}
			
			if (field.isAnnotationPresent(Size.class)) {
				Size annotation = (Size) field.getAnnotation(Size.class);
				field.setAccessible(true);
				double value = 0;
				System.out.println("size");

				if (field.getType() == String.class) {
					value = field.get(object).toString().length();
				} else if (field.getType() == int.class
						|| field.getType() == double.class) {
					value = (double) field.get(object);
				}
				if (value < annotation.min()) {
					errors.add(field.getName() + " must be > "
							+ annotation.min());
				}
				if (value > annotation.max()) {
					errors.add(field.getName() + " must be < "
							+ annotation.max());
				}

			}
			
			if (field.isAnnotationPresent(Min.class)) {
				Min annotation = (Min) field.getAnnotation(Min.class);
				field.setAccessible(true);
				double value = 0;

				if (field.getType() == String.class) {
					value = field.get(object).toString().length();
				} else if (field.getType() == int.class
						|| field.getType() == double.class) {
					value = (double) field.get(object);
				}
				if (value < annotation.value()) {
					errors.add(field.getName() + " must be > "
							+ annotation.value());
				}

			}
			
			if (field.isAnnotationPresent(Max.class)) {
				Max annotation = (Max) field.getAnnotation(Max.class);
				field.setAccessible(true);
				double value = 0;

				if (field.getType() == String.class) {
					value = field.get(object).toString().length();
				} else if (field.getType() == int.class
						|| field.getType() == double.class) {
					value = (double) field.get(object);
				}
				if (value > annotation.value()) {
					errors.add(field.getName() + " must be < "
							+ annotation.value());
				}

			}
			
			if (field.isAnnotationPresent(Email.class)) {
				Email annotation = (Email) field.getAnnotation(Email.class);
				field.setAccessible(true);
				java.util.regex.Pattern pattern = java.util.regex.Pattern
						.compile(annotation.pattern());
				Matcher mathcer = pattern.matcher(field.get(object).toString());
				if (!mathcer.matches()) {
					errors.add(field.getName() + " don`t valid");
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
					errors.add(field.getName() + " don`t valid");
				}
			}
			if (field.isAnnotationPresent(StartFuture.class)) {
				field.setAccessible(true);
				System.out.println("start");
				timestampStart = (Timestamp) field.get(object);
				Date date = new Date();
				Timestamp timestampNow = new Timestamp(date.getTime());
				if (timestampStart.before(timestampNow)) {
					errors.add(field.getName() + " don`t valid");
				}

			}
			if (field.isAnnotationPresent(EndFuture.class)
					&& timestampStart != null) {
				field.setAccessible(true);
				System.out.println("end");
				Timestamp timestampEnd = (Timestamp) field.get(object);
				if (timestampEnd.before(timestampStart)) {
					errors.add(field.getName() + " don`t valid");
				}

			}

		}

		if (errors.isEmpty()) {
			return object;
		} else {
			return null;
		}
	}

	public boolean checkConfirmPass(String pass, String confirmPass) {
		return pass.equals(confirmPass);
	}

}
