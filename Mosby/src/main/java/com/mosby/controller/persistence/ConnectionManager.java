package main.java.com.mosby.controller.persistence;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager {
	private static Logger logger = Logger.getLogger(ConnectionManager.class);

	private static ConnectionManager instance = null;

	private DataSource datasource = null;

	private ConnectionManager() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			datasource = (DataSource) envContext.lookup("jdbc/onlinedb");
		} catch (NamingException e) {
			logger.error(e);
		}
	}

	public static ConnectionManager getInstance()
			throws ClassNotFoundException, SQLException {
		if (instance == null) {
			instance = new ConnectionManager();
		}
		return instance;
	}

	public Connection getConnection() throws SQLException {
		return datasource.getConnection();
	}
}
