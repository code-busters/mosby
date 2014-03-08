package main.java.com.mosby.controller.dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.com.mosby.controller.persistence.ConnectionManager;
import main.java.com.mosby.controller.transformers.ReflectionTransformer;
import main.java.com.mosby.utils.StringUtils;

public class ReflectionDao<T> {

	private Class<T> type;
	private String query;
	private ReflectionTransformer<T> reflectionTransformer;

	public ReflectionDao(Class<T> type) {
		this.type = type;
		this.reflectionTransformer = new ReflectionTransformer<>();
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
		stringBuilder.append(getColumns(false));
		stringBuilder.append("FROM ");

		stringBuilder.append(
				reflectionTransformer.fromFieldToColumnInDB(type
						.getSimpleName())).append("s");
		System.out.println(type.getSimpleName());

		return stringBuilder.toString();
	}

	// -------------------SELECT QUERY WITH WHERE STATEMENT------------
	public String createSelectQuery(String fieldName, Object whereObj) {
		String query = null;

		String tableName = reflectionTransformer.fromFieldToColumnInDB(type
				.getSimpleName()) + "s";
		String tableColumns = getColumns(false);
		String whereColumn = reflectionTransformer
				.fromFieldToColumnInDB(fieldName);
		String whereValue = whereObj.toString();

		query = StringUtils.concat("SELECT ", tableColumns, " FROM ",
				tableName, " WHERE ", whereColumn, "='", whereValue, "'");

		System.out.println(query);

		return query;
	}

	// -----------------------------------------------------------------

	public List<T> selectObjects(String fieldName, Object whereObj) {
		List<T> objects = new ArrayList<>();
		try {
			PreparedStatement stmt = (PreparedStatement) ConnectionManager
					.getInstance().getConnection()
					.prepareStatement(createSelectQuery(fieldName, whereObj));
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				objects.add(reflectionTransformer.fromRStoObject(
						type.newInstance(), rs, type));
			}
			rs.close();
			stmt.close();

		} catch (ClassNotFoundException | SQLException | InstantiationException
				| IllegalAccessException e) {
			e.printStackTrace();
		}
		return objects;
	}

	// -------------------INSERT QUERY WITH VALUES---------------------
	public String createInsertQuery() {
		String query = null;

		String tableName = reflectionTransformer.fromFieldToColumnInDB(type
				.getSimpleName()) + "s";
		String tableColumns = getColumns(true);

		query = StringUtils.concat("INSERT INTO ", tableName, " VALUES (",
				tableColumns, ")");

		System.out.println(query);

		return query;
	}

	// ----------------------------------------------------------------

	public void insertObjects(T object) {
		PreparedStatement preparedStatement;
		try {
			Connection connection = ConnectionManager.getInstance()
					.getConnection();
			preparedStatement = (PreparedStatement) connection
					.prepareStatement(createInsertQuery());

			preparedStatement = (PreparedStatement) reflectionTransformer
					.fromObjectToStatement(preparedStatement, type, object);

			preparedStatement.addBatch();
			preparedStatement.executeBatch();
		} catch (SQLException | IllegalArgumentException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private String getColumns(boolean hasValues) {
		StringBuilder stringBuilder = new StringBuilder();

		for (Field field : type.getDeclaredFields()) {
			if (hasValues) {
				stringBuilder.append("?, ");
			} else {
				stringBuilder.append(field.getName()).append(", ");
			}
		}
		stringBuilder.deleteCharAt(stringBuilder.length() - 2);
		return reflectionTransformer.fromFieldToColumnInDB(stringBuilder
				.toString());
	}
}
