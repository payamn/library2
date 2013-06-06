package domain.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnector {

	private static SQLConnector sql = null;
	private static Connection connection = null;
	private SQLConnector(String user, String pass, String dbName) {
		System.out.println("Comment : SQLConnector instantiating ...");
		// load the JDBC Driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			System.out.println("Unable to load MySQL JDBC driver");
		}

		// connecting to the database
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/"+dbName, user, pass);
		} catch (SQLException e) {
			System.out.println("Error in initializing sql , See SQLConnector.java in sql package .");
			e.printStackTrace();
		}
	}
	public static SQLConnector getInstance(String user, String pass, String dbName) {
		if(sql == null) {
			sql = new SQLConnector(user, pass, dbName);
		}
		return sql;
	}
	public static Connection getConnection() {
		if(connection == null)
			System.out.println("A very bad error has been occured in SQLConnector .");
		return connection;
	}
}

