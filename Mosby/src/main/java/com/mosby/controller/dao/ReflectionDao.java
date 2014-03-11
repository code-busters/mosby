package main.java.com.mosby.controller.dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.com.mosby.controller.persistence.ConnectionManager;
import main.java.com.mosby.controller.transformers.ReflectionTransformer;

public class ReflectionDao<T> {

	private Class<T> type;
	private String query;
	private ReflectionTransformer<T> reflectionTransformer;
	private QueryStatements<T> queryStatements;

	public ReflectionDao(Class<T> type) {
		this.type = type;
		this.reflectionTransformer = new ReflectionTransformer<>();
		this.queryStatements = new QueryStatements<>(type);
		this.query = null;
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

	public void setQuery(String queryStatement) {
		this.query = queryStatement;
	}

	public List<T> selectObjects(String fieldName, Object whereObj) {
		List<T> objects = new ArrayList<>();
		try {
			query = queryStatements.createSelectQuery(fieldName, whereObj);

			Connection connection = ConnectionManager.getInstance()
					.getConnection();
			PreparedStatement preparedStatement = (PreparedStatement) connection
					.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				objects.add(reflectionTransformer.fromRStoObject(
						type.newInstance(), resultSet, type));
			}

			resultSet.close();
			preparedStatement.close();
		} catch (ClassNotFoundException | SQLException | InstantiationException
				| IllegalAccessException e) {
			e.printStackTrace();
		}
		return objects;
	}

	public void insertObjects(T object) {
		try {
			query = queryStatements.createInsertQuery();

			Connection connection = ConnectionManager.getInstance()
					.getConnection();
			PreparedStatement preparedStatement = (PreparedStatement) connection
					.prepareStatement(query);

			preparedStatement = (PreparedStatement) reflectionTransformer
					.fromObjectToStatement(preparedStatement, type, object);

			preparedStatement.addBatch();
			preparedStatement.executeBatch();

			preparedStatement.close();
		} catch (SQLException | IllegalArgumentException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateObjects(T object, String whereField, Object whereValue) {
		try {
			query = queryStatements.createUpdateQuery(whereField);

			Connection connection = ConnectionManager.getInstance()
					.getConnection();
			PreparedStatement preparedStatement = (PreparedStatement) connection
					.prepareStatement(query);

			int i = 0;
			for (Field field : type.getDeclaredFields()) {
				if (!field.getName().equals(whereField)) {
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(
							field.getName(), type);
					Method method = propertyDescriptor.getReadMethod();
					Object value = method.invoke(object);

					preparedStatement.setObject(++i, value);
				}
			}
			preparedStatement.setObject(++i, whereValue);

			preparedStatement.addBatch();
			preparedStatement.executeBatch();
		} catch (SQLException | IntrospectionException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void deleteObjects(String whereField, Object whereValue) {
		try {
			query = queryStatements.createDeleteQuery(whereField);
			
			Connection connection = ConnectionManager.getInstance()
					.getConnection();
			PreparedStatement preparedStatement = (PreparedStatement) connection
					.prepareStatement(query);

			preparedStatement.setObject(1, whereValue);
			
			preparedStatement.addBatch();
			preparedStatement.executeBatch();
		} catch (SQLException | IllegalArgumentException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
