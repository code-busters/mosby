package main.java.com.mosby.controller.dao;

import main.java.com.mosby.model.annotations.dao.Column;
import main.java.com.mosby.model.annotations.dao.Key;
import main.java.com.mosby.model.annotations.dao.Table;
import main.java.com.mosby.utils.StringUtils;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;

public class QueryStatements<T> {

    private static Logger logger = Logger.getLogger(QueryStatements.class.getName());

    private Class<T> type;

    public QueryStatements(Class<T> type) {
        this.type = type;
    }

    public String createSelectQuery(Object... whereArguments) {
        String query = null;
        String tableName = type.getAnnotation(Table.class).name();
        String tableColumns = getColumns(false);

        if (whereArguments.length == 0 || (whereArguments[0].toString().equals(""))) {
            query = StringUtils.concat("SELECT ", tableColumns, "FROM ",
                    tableName);
        } else {
            query = StringUtils.concat("SELECT ", tableColumns, "FROM ",
                    tableName, " WHERE ", getWhereColumns(whereArguments));
        }

        System.out.println(query);

        return query;
    }

    public String createAggregateSelectQuery(String aggregateFunction, Object... whereArguments) {
        String query = null;
        String tableName = type.getAnnotation(Table.class).name();

        if (whereArguments.length == 0 || (whereArguments[0].toString().equals(""))) {
            query = StringUtils.concat("SELECT ", aggregateFunction, " as aggregate_function FROM ",
                    tableName);
        } else {
            query = StringUtils.concat("SELECT ", aggregateFunction, " as aggregate_function FROM ",
                    tableName, " WHERE ", getWhereColumns(whereArguments));
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

    public String createUpdateQuery() {
        String query = null;

        String tableName = type.getAnnotation(Table.class).name();
        String tableColumns = getUpdateColumns();

        query = StringUtils.concat("UPDATE ", tableName, " SET ", tableColumns,
                "WHERE id=?");

        System.out.println(query);

        return query;
    }

    public String createDeleteQuery() {
        String query = null;

        String tableName = type.getAnnotation(Table.class).name();

        query = StringUtils.concat("DELETE FROM ", tableName, " WHERE id=?");

        System.out.println(query);

        return query;
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
        tableColumns = stringBuilder.toString();

        return tableColumns;
    }

    private String getWhereColumns(Object...whereArguments) {
        String whereStatement = null;
        StringBuilder stringBuilder = new StringBuilder();

        int divideWhereArgs = 0;

        for (int i = 1; i < whereArguments.length; i += 2) {
            if (isLikeOperation(whereArguments[i].toString())){
                divideWhereArgs = i;
            } else {
                break;
            }
        }

        if (divideWhereArgs > 0) {
            stringBuilder.append("(").append(getLikeColumns(divideWhereArgs, whereArguments)).append(") AND ");
            divideWhereArgs += 2;
        } else {
            divideWhereArgs = 1;
        }

        for (int i = divideWhereArgs; i < whereArguments.length; i += 2) {
            stringBuilder.append(whereArguments[i - 1].toString()).append("'").append(whereArguments[i].toString()).append("' AND ");
        }

        stringBuilder.delete(stringBuilder.length() - 4, stringBuilder.length() - 1);
        whereStatement = stringBuilder.toString();

        return whereStatement;
    }

    private String getLikeColumns(int divideWhereArgs, Object...whereArguments) {
        String whereStatement = null;
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 1; i <= divideWhereArgs; i += 2) {
            if (isLikeOperation(whereArguments[i].toString())){
                stringBuilder.append(whereArguments[i - 1].toString()).append(" LIKE '").append(whereArguments[i].toString()).append("' OR ");
            } else {
                break;
            }
        }

        stringBuilder.delete(stringBuilder.length() - 4, stringBuilder.length());
        whereStatement = stringBuilder.toString();

        return whereStatement;
    }

    private String getUpdateColumns() {
        String tableColumns = null;
        StringBuilder stringBuilder = new StringBuilder();

        for (Field field : type.getDeclaredFields()) {
            String fieldName = null;
            if (field.isAnnotationPresent(Column.class)) {
                Column annotation = (Column) field.getAnnotation(Column.class);
                fieldName = annotation.name();
                if (!fieldName.equals("id")) {
                    stringBuilder.append(fieldName).append("=?, ");
                }
            } else if (field.isAnnotationPresent(Key.class)) {
                Key annotation = (Key) field.getAnnotation(Key.class);
                fieldName = annotation.name();
                stringBuilder.append(fieldName).append("=?, ");
            }
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 2);
        tableColumns = stringBuilder.toString();

        return tableColumns;
    }

    private boolean isLikeOperation(String columnValue) {
        boolean result = false;
        if (columnValue.charAt(columnValue.length() - 1) == '%' || columnValue.charAt(columnValue.length() - 1) == '%'){
            result = true;
        }
        return result;
    }

}
