package main.java.com.mosby.controller.persistence;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Connection;

public class ConnectionManager {

	private static ConnectionManager instance = null;

	private Connection conn = null;

	private ConnectionManager() {
		
		Properties connectionPr = new Properties();
		try {
			InputStream is = new FileInputStream(
					"E:/файли/Олексій/workspaceEE/Mosby/WebContent/WEB-INF/classes/connection.properties");
			connectionPr.load(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Class.forName(connectionPr.getProperty("connection.driver"));
			conn = ((Connection) DriverManager.getConnection(
					connectionPr.getProperty("connection.url"),
					connectionPr.getProperty("connection.user"),
					connectionPr.getProperty("connection.pass")));
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	public static ConnectionManager getInstance()
			throws ClassNotFoundException, SQLException {
		if (instance == null) {
			instance = new ConnectionManager();
		}
		return instance;
	}

	public Connection getConn() {
		return conn;
	}
}
