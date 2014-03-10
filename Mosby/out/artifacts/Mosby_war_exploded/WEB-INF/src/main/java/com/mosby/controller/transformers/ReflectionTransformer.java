package main.java.com.mosby.controller.transformers;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReflectionTransformer<T> {

	public T fromRStoObject(T object, ResultSet resultSet, Class<T> type) {

		for (Field field : type.getDeclaredFields()) {

			Object value = null;
			try {
				if (field.getType().equals(boolean.class)) {
					int state = (int) resultSet
							.getObject(fromFieldToColumnInDB(field.getName()));
					if (state == 0) {
						value = new Boolean(false);
					} else if (state == 1) {
						value = new Boolean(true);
					}
				} else {
					value = resultSet.getObject(fromFieldToColumnInDB(field
							.getName()));
				}

				PropertyDescriptor propertyDescriptor;
				propertyDescriptor = new PropertyDescriptor(field.getName(),
						type);
				Method method = propertyDescriptor.getWriteMethod();
				method.invoke(object, value);
			} catch (IntrospectionException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException
					| SQLException e) {
				e.printStackTrace();
			}
		}
		return object;
	}

	public PreparedStatement fromObjectToStatement(
			PreparedStatement preparedStatement, Class<T> type, T object) {
		int columnIndex = 0;

		for (Field field : type.getDeclaredFields()) {
			PropertyDescriptor propertyDescriptor;
			try {
				propertyDescriptor = new PropertyDescriptor(field.getName(),
						type);
				Method method = propertyDescriptor.getReadMethod();
				Object value = method.invoke(object);
				preparedStatement.setObject(++columnIndex, value);
			} catch (IntrospectionException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException
					| SQLException e) {
				e.printStackTrace();
			}
		}

		return preparedStatement;
	}

	public String fromFieldToColumnInDB(String line) {
		String dbColumn = "";
		StringBuilder currentLine = new StringBuilder();

		for (int i = 0; i < line.length(); ++i) {
			if (Character.isUpperCase(line.charAt(i)) && i != 0) {
				currentLine.append("_");
			}
			currentLine.append(line.charAt(i));
		}
		dbColumn = currentLine.toString().toLowerCase();

		return dbColumn;
	}

}
