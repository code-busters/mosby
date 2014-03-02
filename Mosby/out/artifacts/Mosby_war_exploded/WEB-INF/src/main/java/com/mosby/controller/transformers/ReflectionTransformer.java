package main.java.com.mosby.controller.transformers;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReflectionTransformer<T> {
	
	private StringBuilder line;
	
	public T fromRStoObject(T obj, ResultSet rs, Class<T> type) {

		for (Field field : type.getDeclaredFields()) {

			Object value;
			try {
				value = rs.getObject(fromFieldToColumnInDB(field.getName()));
			PropertyDescriptor propertyDescriptor;
				propertyDescriptor = new PropertyDescriptor(
						field.getName(), type);
				Method method = propertyDescriptor.getWriteMethod();
				method.invoke(obj, value);
			} catch (IntrospectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return obj;
	}

	public String fromFieldToColumnInDB(String line) {

		this.line = new StringBuilder();
		
		for (int i = 0; i < line.length(); ++i) {
			if (Character.isUpperCase(line.charAt(i)) && i != 0) {
				this.line.append("_");
			} 
			
			this.line.append(line.charAt(i));
		}
		
		return this.line.toString().toLowerCase();
	}

}
