package main.java.com.mosby.controller.dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import main.java.com.mosby.controller.persistence.*;
import main.java.com.mosby.controller.transformers.*;
import com.mysql.jdbc.PreparedStatement;

public class ReflectionDao<T> {

	private Class<T> type;
	private String query;
	private ReflectionTransformer<T> reflectionTransformer = new ReflectionTransformer<>();
	private HashMap<Integer, T> hashMap = new HashMap<>();

	public ReflectionDao(Class<T> type) {
		this.type = type;
		System.out.println(this.type);
		this.query = createQuery();
	}

	public Class<T> getType() {
		return type;
	}

	public void setType(Class<T> type) {
		this.type = type;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery() {
		this.query = createQuery();
	}

	public String createQuery() {
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("SELECT ");
		stringBuilder.append(getColumns());
		stringBuilder.append("FROM ");
		
		stringBuilder.append(reflectionTransformer.fromFieldToColumnInDB(type.getSimpleName())).append("s");
		System.out.println(type.getSimpleName());

		return stringBuilder.toString();
	}

	public void selectAll() {
		try {
			PreparedStatement stmt = (PreparedStatement) ConnectionManager
					.getInstance().getConn().prepareStatement(this.query);
			ResultSet rs = stmt.executeQuery();
			setHashMap(new HashMap<Integer, T>());

			while (rs.next()) {
				try {
					getHashMap().put(
							rs.getInt("id"),
							getReflectionTransformer().fromRStoObject(
									type.newInstance(), rs, type));
				} catch (InstantiationException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			rs.close();
			stmt.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<T> createObjects(ResultSet resultSet) throws SQLException,
			InstantiationException, IllegalAccessException,
			IntrospectionException, IllegalArgumentException,
			InvocationTargetException {

		List<T> objects = new ArrayList<>();

		while (resultSet.next()) {

			T currentObject = type.newInstance();

			for (Field field : type.getDeclaredFields()) {

				Object value = resultSet.getObject(field.getName());

				System.out.println(value);

				PropertyDescriptor propertyDescriptor = new PropertyDescriptor(
						field.getName(), type);
				Method method = propertyDescriptor.getWriteMethod();

				method.invoke(currentObject, value);
			}

			objects.add(currentObject);
		}

		return objects;
	}

	public ReflectionTransformer<T> getReflectionTransformer() {
		return reflectionTransformer;
	}

	public void setReflectionTransformer(
			ReflectionTransformer<T> reflectionTransformer) {
		this.reflectionTransformer = reflectionTransformer;
	}

	public HashMap<Integer, T> getHashMap() {
		return hashMap;
	}

	public void setHashMap(HashMap<Integer, T> hashMap) {
		this.hashMap = hashMap;
	}

	private String getColumns() {
		StringBuilder stringBuilder = new StringBuilder();

		for (Field field : type.getDeclaredFields()) {
			stringBuilder.append(field.getName()).append(", ");
		}
		stringBuilder.deleteCharAt(stringBuilder.length() - 2);
		return reflectionTransformer.fromFieldToColumnInDB(stringBuilder
				.toString());
	}
}
