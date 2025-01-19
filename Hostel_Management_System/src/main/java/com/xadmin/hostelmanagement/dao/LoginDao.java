package com.xadmin.hostelmanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
	private static String jdbcURL ="jdbc:mysql://localhost:3306/Hostel_Management_System";
	private static String jdbcUsername ="root";
	private static String jdbcPassword ="mysql";
	private static String jdbcDriver ="com.mysql.cj.jdbc.Driver";
	private static final String SELECT_USER_BY_ID_PASSWORD = "SELECT * FROM NEWREGISTERATION WHERE ID = ? AND PASSWORD = ?;";
	protected static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName(jdbcDriver);
			connection = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
		} catch (SQLException e) {
			 throw new RuntimeException("Cannot connect to database", e);


		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
	public static boolean validate(int id , String password){
		boolean status=false;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_ID_PASSWORD);){
			statement.setInt(1, id);
			statement.setString(2,password);
			ResultSet rs = statement.executeQuery();
			status = rs.next();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
		}

}
