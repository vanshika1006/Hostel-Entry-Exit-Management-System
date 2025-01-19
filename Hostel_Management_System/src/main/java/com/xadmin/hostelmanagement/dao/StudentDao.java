package com.xadmin.hostelmanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.xadmin.hostelmanagement.bean.Student;

public class StudentDao {
	private static String jdbcURL ="jdbc:mysql://localhost:3306/Hostel_Management_System";
	private static String jdbcUsername ="root";
	private static String jdbcPassword ="mysql";
	private static String jdbcDriver ="com.mysql.cj.jdbc.Driver";
	private static final String SELECT_USER = "SELECT ID, TIME_IN , TIME_OUT FROM STUDENT WHERE ID = ?;";
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
	public static List<Student> selectAllPrevRec(int id) throws SQLException {
		List<Student> Student = new ArrayList<>();
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER);) {
			preparedStatement.setInt(1,id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Timestamp time_in = rs.getTimestamp("Time_in");
				Timestamp time_out = rs.getTimestamp("Time_out");
				Student.add(new Student(id,time_in,time_out));
			}

		}
		return Student;
	}

}
