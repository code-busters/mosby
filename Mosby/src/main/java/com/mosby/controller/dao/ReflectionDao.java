package main.java.com.mosby.controller.dao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



import org.apache.log4j.Logger;

import main.java.com.mosby.controller.persistence.ConnectionManager;
import main.java.com.mosby.controller.transformers.ReflectionTransformer;


public class ReflectionDao<T> {

	private static Logger logger = Logger.getLogger(ReflectionDao.class);
	
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
	
	public List<T> selectObjects(Object... whereArguments) {//throws Exception {
		List<T> objects = new ArrayList<>();
		try (Connection connection = ConnectionManager.getInstance().getConnection()) {
			connection.setAutoCommit(false);
			query = queryStatements.createSelectQuery(whereArguments);

			PreparedStatement preparedStatement = (PreparedStatement) connection
					.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			connection.commit();
			
			while (resultSet.next()) {
				objects.add(reflectionTransformer.fromRStoObject(
						type.newInstance(), resultSet, type, false));
			}

			resultSet.close();
			preparedStatement.close();
		} catch (ClassNotFoundException | SQLException | InstantiationException
				| IllegalAccessException e) {
			logger.error("SQL Select exception", e);
		}
		return objects;
	}
	
	public List<Long> selectAggregateObjects(String aggregateFunction, Object... whereArguments) {//throws Exception {
		List<Long> objects = new ArrayList<>();
		Long integer = 0L;
		ReflectionTransformer<Long> integerTransformer = new ReflectionTransformer<>();
		try (Connection connection = ConnectionManager.getInstance().getConnection()) {
			connection.setAutoCommit(false);
			query = queryStatements.createAggregateSelectQuery(aggregateFunction, whereArguments);
			
			PreparedStatement preparedStatement = (PreparedStatement) connection
					.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			connection.commit();
			
			while (resultSet.next()) {
				objects.add(integerTransformer.fromRStoObject(
						integer, resultSet, Long.class, true));
			}

			resultSet.close();
			preparedStatement.close();
		} catch (ClassNotFoundException | SQLException e) {
			logger.error("SQL Select exception", e);
		}
		return objects;
	}

	public int insertObjects(T object) {//throws Exception {
		int generatedId = -1;
		try (Connection connection = ConnectionManager.getInstance().getConnection()) {
			connection.setAutoCommit(false);
			query = queryStatements.createInsertQuery();

			PreparedStatement preparedStatement = (PreparedStatement) connection
					.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			preparedStatement = (PreparedStatement) reflectionTransformer
					.fromObjectToStatement(preparedStatement, type, object,
							false);

			preparedStatement.addBatch();
			preparedStatement.executeBatch();
			connection.commit();
			
			ResultSet keys = preparedStatement.getGeneratedKeys();
			keys.next();
			generatedId = keys.getInt(1);

			keys.close();
			preparedStatement.close();
		} catch (SQLException | IllegalArgumentException
				| ClassNotFoundException e) {
			logger.error("SQL Insert exception", e);
		}
		return generatedId;
	}

	public void updateObjects(T object) {//throws Exception {
		try (Connection connection = ConnectionManager.getInstance().getConnection()) {
			connection.setAutoCommit(false);
			query = queryStatements.createUpdateQuery();

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
			connection.commit();

			preparedStatement.close();
		} catch (SQLException | IllegalArgumentException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalAccessException
				| InvocationTargetException e) {
			logger.error("SQL Update exception", e);
		}
	}

	public void deleteObjects(T object) {//throws Exception {
		try (Connection connection = ConnectionManager.getInstance().getConnection()) {
			connection.setAutoCommit(false);

			query = queryStatements.createDeleteQuery();

			PreparedStatement preparedStatement = (PreparedStatement) connection
					.prepareStatement(query);
			Method getIdMethod = type.getDeclaredMethod("getId");
			preparedStatement.setObject(1, getIdMethod.invoke(object));

			preparedStatement.addBatch();
			preparedStatement.executeBatch();
			connection.commit();

			preparedStatement.close();
		} catch (SQLException | IllegalArgumentException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalAccessException
				| InvocationTargetException e) {
			logger.error("SQL Delete exception", e);
		}
	}

}
