package main.java.com.mosby.controller.persistence;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager {
    private static Logger log = Logger.getLogger(ConnectionManager.class);

	private static ConnectionManager instance = null;

	private Connection conn = null;

	private ConnectionManager() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource datasource = (DataSource) envContext.lookup("jdbc/onlinedb");
            conn = datasource.getConnection();
        } catch (NamingException | SQLException e) {
            log.error(e);
        }
    }

	public static ConnectionManager getInstance()
			throws ClassNotFoundException, SQLException {
		if (instance == null) {
			instance = new ConnectionManager();
		}
		return instance;
	}

	public Connection getConnection() {
		return conn;
	}
}
