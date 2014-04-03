package main.java.com.mosby.controller.transformers;

import main.java.com.mosby.controller.dao.ReflectionDao;
import main.java.com.mosby.model.annotations.dao.Column;
import main.java.com.mosby.model.annotations.dao.Key;
import org.apache.log4j.Logger;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReflectionTransformer<T> {

    private static Logger logger = Logger
            .getLogger(ReflectionTransformer.class);

    public T fromRStoObject(T object, ResultSet resultSet, Class<T> type, int depth,
                            boolean hasAggregateFunction) {
        try {
            if (hasAggregateFunction) {
                BigDecimal decimal = new BigDecimal(resultSet.getObject("aggregate_function").toString());
                object = type.cast(decimal);
            } else {
                for (Field field : type.getDeclaredFields()) {

                    Object value = null;
                    if (field.isAnnotationPresent(Column.class)) {
                        if (field.getType().equals(boolean.class)) {
                            int state = (int) resultSet.getObject(field
                                    .getAnnotation(Column.class).name());
                            if (state == 0) {
                                value = new Boolean(false);
                            } else if (state == 1) {
                                value = new Boolean(true);
                            }
                        } else {

                            value = resultSet.getObject(field.getAnnotation(
                                    Column.class).name());
                        }
                    } else if (field.isAnnotationPresent(Key.class)) {
                        value = resultSet.getObject(field.getAnnotation(
                                Key.class).name());
                        if (value != null && depth != 0) {
                            int currentId = Integer.parseInt(value.toString());
                            value = new ReflectionDao<>(field.getType())
                                    .selectObjects(depth, "id=", currentId).get(0);
                        } else {
                        	value = null;
                        }
                    }
                    if (field.isAnnotationPresent(Key.class)
                            || field.isAnnotationPresent(Column.class)) {
                        PropertyDescriptor propertyDescriptor;
                        propertyDescriptor = new PropertyDescriptor(
                                field.getName(), type);
                        Method method = propertyDescriptor.getWriteMethod();
                        method.invoke(object, value);
                    }
                }
            }
        } catch (IntrospectionException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException
                | SQLException e) {
            logger.error("From ResultSet to Object exception", e);
        }
        return object;
    }

    public PreparedStatement fromObjectToStatement(
            PreparedStatement preparedStatement, Class<T> type, T object,
            boolean isUpdate) {
        int columnIndex = 0;
        System.out.println(object);
        for (Field field : type.getDeclaredFields()) {
            if (!(isUpdate && field.getName().equals("id"))) {
                PropertyDescriptor propertyDescriptor;
                try {
                    if (field.isAnnotationPresent(Column.class)) {
                        propertyDescriptor = new PropertyDescriptor(
                                field.getName(), type);
                        Method method = propertyDescriptor.getReadMethod();
                        System.out.println(method.getName().toString());
                        Object value = method.invoke(object);
                        if (value != null) {
                            System.out.println(value);
                        } else {
                            System.out.println("Null");
                        }
                        preparedStatement.setObject(++columnIndex, value);

                    } else if (field.isAnnotationPresent(Key.class)) {
                        Class<?> fieldClass = field.getType();
                        System.out.println(fieldClass.toString());

                        propertyDescriptor = new PropertyDescriptor(
                                field.getName(), type);
                        Method mainMethod = propertyDescriptor.getReadMethod();
                        System.out.println(mainMethod.getName().toString());
                        Object fieldObject = mainMethod.invoke(object);

                        if (fieldObject != null) {
                            System.out.println(fieldObject.toString());

                            Method currentObjectMethod = fieldClass
                                    .getDeclaredMethod("getId");
                            System.out.println(currentObjectMethod.getName()
                                    .toString());
                            Object value = currentObjectMethod
                                    .invoke(fieldObject);

                            System.out.println(value);

                            preparedStatement.setObject(++columnIndex, value);
                        } else {
                            System.out.println("Null");
                            preparedStatement.setObject(++columnIndex, null);
                        }

                    }
                } catch (IntrospectionException | IllegalAccessException
                        | IllegalArgumentException | InvocationTargetException
                        | SQLException | SecurityException
                        | NoSuchMethodException e) {
                    logger.error("From Object to Statement exception", e);
                }
                System.out.println(columnIndex);
            }
        }
        return preparedStatement;
    }

    public int columnCount(Class<T> type) {
        int columnCount = 0;
        for (Field field : type.getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class)
                    || field.isAnnotationPresent(Key.class)) {
                columnCount++;
            }
        }
        return columnCount;
    }

}
