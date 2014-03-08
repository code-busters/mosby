package main.java.com.mosby.controller.dao;

import java.lang.reflect.Field;

import main.java.com.mosby.controller.transformers.ReflectionTransformer;
import main.java.com.mosby.utils.StringUtils;

public class QueryStatements<T> {

	private Class<T> type;
	private ReflectionTransformer<T> reflectionTransformer;

	public QueryStatements(Class<T> type) {
		this.type = type;
		this.reflectionTransformer = new ReflectionTransformer<>();
	}

	public String createSelectQuery(String fieldName, Object whereObj) {
		String query = null;

		String tableName = reflectionTransformer.fromFieldToColumnInDB(type
				.getSimpleName()) + "s";
		String tableColumns = getColumns(false);
		String whereColumn = reflectionTransformer
				.fromFieldToColumnInDB(fieldName);
		String whereValue = whereObj.toString();

		query = StringUtils.concat("SELECT ", tableColumns, "FROM ", tableName,
				" WHERE ", whereColumn, "='", whereValue, "'");

		System.out.println(query);

		return query;
	}

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

	public String createUpdateQuery() {
		// final String UPDATE_BOOK_AMOUNT =
		// "UPDATE Books SET amount=? WHERE id=?";
		String query = null;

		String tableName = reflectionTransformer.fromFieldToColumnInDB(type
				.getSimpleName()) + "s";
		String tableColumns = getColumns(true);

		query = StringUtils.concat("UPDATE ", tableName, " SET ", tableColumns,
				")");

		System.out.println(query);

		return query;
	}

	private String getColumns(boolean hasValues) {
		String tableColumns = null;
		StringBuilder stringBuilder = new StringBuilder();

		for (Field field : type.getDeclaredFields()) {
			if (hasValues) {
				stringBuilder.append("?, ");
			} else {
				stringBuilder.append(field.getName()).append(", ");
			}
		}
		stringBuilder.deleteCharAt(stringBuilder.length() - 2);
		tableColumns = reflectionTransformer
				.fromFieldToColumnInDB(stringBuilder.toString());

		return tableColumns;
	}

}
