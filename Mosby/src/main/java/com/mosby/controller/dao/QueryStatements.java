package main.java.com.mosby.controller.dao;

import java.lang.reflect.Field;

import main.java.com.mosby.controller.transformers.ReflectionTransformer;
import main.java.com.mosby.model.annotations.dao.Column;
import main.java.com.mosby.model.annotations.dao.Key;
import main.java.com.mosby.model.annotations.dao.Table;
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
		String tableName = type.getAnnotation(Table.class).name();
		String tableColumns = getColumns(false);

		if (fieldName.equals("")) {
			query = StringUtils.concat("SELECT ", tableColumns, "FROM ",
					tableName);
		} else {
			String whereColumn = reflectionTransformer
					.fromFieldToColumnInDB(fieldName);
			String whereValue = whereObj.toString();
			query = StringUtils.concat("SELECT ", tableColumns, "FROM ",
					tableName, " WHERE ", whereColumn, "='", whereValue, "'");
		}

		System.out.println(query);

		return query;
	}

	public String createInsertQuery() {
		String query = null;

		String tableName = type.getAnnotation(Table.class).name();
		String tableColumns = getColumns(true);

		query = StringUtils.concat("INSERT INTO ", tableName, " VALUES (",
				tableColumns, ")");

		System.out.println(query);

		return query;
	}

	public String createUpdateQuery(String whereField) {
		String query = null;

		String whereColumn = reflectionTransformer
				.fromFieldToColumnInDB(whereField);
		String tableName = reflectionTransformer.fromFieldToColumnInDB(type
				.getSimpleName()) + "s";
		String tableColumns = getUpdateColumns(whereField);

		query = StringUtils.concat("UPDATE ", tableName, " SET ", tableColumns,
				"WHERE ", whereColumn, "=?");

		System.out.println(query);

		return query;
	}

	public String createDeleteQuery(String whereField) {
		String query = null;

		String whereColumn = reflectionTransformer
				.fromFieldToColumnInDB(whereField);
		String tableName = reflectionTransformer.fromFieldToColumnInDB(type
				.getSimpleName()) + "s";

		query = StringUtils.concat("DELETE FROM ", tableName, " WHERE ",
				whereColumn, "=?");

		System.out.println(query);

		return query;
	}

	private String getUpdateColumns(String whereField) {
		String tableColumns = null;
		StringBuilder stringBuilder = new StringBuilder();

		for (Field field : type.getDeclaredFields()) {
			String fieldName = reflectionTransformer.fromFieldToColumnInDB(
					field.getName()).toString();
			if (!whereField.equals(fieldName)) {
				stringBuilder.append(fieldName).append("=?, ");
			}
		}
		stringBuilder.deleteCharAt(stringBuilder.length() - 2);
		tableColumns = stringBuilder.toString();

		return tableColumns;
	}

	private String getColumns(boolean hasValues) {
		String tableColumns = null;
		StringBuilder stringBuilder = new StringBuilder();

		for (Field field : type.getDeclaredFields()) {

			if (hasValues) {
				if (field.isAnnotationPresent(Column.class)
						|| field.isAnnotationPresent(Key.class)) {
					stringBuilder.append("?, ");
				}
			} else {
				if (field.isAnnotationPresent(Column.class)) {
					Column annotation = (Column) field
							.getAnnotation(Column.class);
					stringBuilder.append(annotation.name()).append(", ");
					System.out.println("\t" + annotation.name());
				} else if (field.isAnnotationPresent(Key.class)) {
					Key annotation = (Key) field.getAnnotation(Key.class);
					stringBuilder.append(annotation.name()).append(", ");
					System.out.println("\t" + annotation.name());
				}
			}
		}
		stringBuilder.deleteCharAt(stringBuilder.length() - 2);
		tableColumns = reflectionTransformer
				.fromFieldToColumnInDB(stringBuilder.toString());

		return tableColumns;
	}

}
