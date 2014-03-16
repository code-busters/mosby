package main.java.com.mosby.controller.dao;

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

import com.mysql.jdbc.Statement;

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

	public int insertObjects(T object) {
		int generatedId = -1;
		try {
			query = queryStatements.createInsertQuery();

			Connection connection = ConnectionManager.getInstance()
					.getConnection();
			PreparedStatement preparedStatement = (PreparedStatement) connection
					.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			preparedStatement = (PreparedStatement) reflectionTransformer
					.fromObjectToStatement(preparedStatement, type, object,
							false);

			preparedStatement.addBatch();
			preparedStatement.executeBatch();

			ResultSet keys = preparedStatement.getGeneratedKeys();
			keys.next();
			generatedId = keys.getInt(1);

			keys.close();
			preparedStatement.close();
		} catch (SQLException | IllegalArgumentException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}
		return generatedId;
	}

	public void updateObjects(T object) {
		try {
			query = queryStatements.createUpdateQuery();

			Connection connection = ConnectionManager.getInstance()
					.getConnection();
			PreparedStatement preparedStatement = (PreparedStatement) connection
					.prepareStatement(query);
			preparedStatement = (PreparedStatement) reflectionTransformer
					.fromObjectToStatement(preparedStatement, type, object,
							true);
			int whereColumnIndex = reflectionTransformer.columnCount(type);

			Method getIdMethod = type.getDeclaredMethod("getId");
			preparedStatement.setObject(whereColumnIndex,
					getIdMethod.invoke(object));

			preparedStatement.addBatch();
			preparedStatement.executeBatch();
		} catch (SQLException | IllegalArgumentException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalAccessException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public void deleteObjects(T object) {
		try {
			query = queryStatements.createDeleteQuery();

			Connection connection = ConnectionManager.getInstance()
					.getConnection();
			PreparedStatement preparedStatement = (PreparedStatement) connection
					.prepareStatement(query);
			Method getIdMethod = type.getDeclaredMethod("getId");
			preparedStatement.setObject(1, getIdMethod.invoke(object));

			preparedStatement.addBatch();
			preparedStatement.executeBatch();
		} catch (SQLException | IllegalArgumentException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalAccessException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}

}
