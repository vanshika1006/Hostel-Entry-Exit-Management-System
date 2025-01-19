package com.xadmin.hostelmanagement.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xadmin.hostelmanagement.bean.User;

public class UserDao {
	private String jdbcURL ="jdbc:mysql://localhost:3306/Hostel_Management_System";
	private String jdbcUsername ="root";
	private String jdbcPassword ="mysql";
	private String jdbcDriver ="com.mysql.cj.jdbc.Driver";

	private static final String INSERT_USERS_SQL = "INSERT INTO NEWREGISTERATION" + "(NAME,PHONE_NO,EMAIL,ID,PASSWORD,USERTYPE)VALUES" + "(?,?,?,?,?,?);";
	private static final String SELECT_USER_BY_ID = "SELECT NAME,PHONE_NO,EMAIL,PASSWORD,USERTYPE FROM NEWREGISTERATION WHERE ID = ?;";
	private static final String DELETE_USERS_SQL = "DELETE FROM NEWREGISTERATION WHERE ID = ?;";
	private static final String SELECT_ALL_USERS = "SELECT * FROM NEWREGISTERATION";
	private static final String UPDATE_USERS_SQL = "UPDATE NEWREGISTERATION SET NAME = ?, PHONE_NO = ?, EMAIL = ?, PASSWORD = ?, WHERE ID =?;";
	//private static final String SELECT_USER_BY_ID_PASSWORD = "SELECT USER FROM NEWREGISTERATION WHERE ID = ? AND PASSWORD = ?;";


	protected Connection getConnection() {
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

	// insert User
	public void insertUser(User user) throws SQLException {
		System.out.println(INSERT_USERS_SQL);
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getPhone_no());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setInt(4, user.getId());
			preparedStatement.setString(5, user.getPassword());
			preparedStatement.setString(6, user.getUserType());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			 throw new RuntimeException("Cannot connect to database", e);
		}

	}

	// Select User
	public User selectUser(int id) {
		User user = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String name = rs.getString("name");
				String email = rs.getString("email");
				String phone_no = rs.getString("phone_no");
				String password = rs.getString("password");
				String userType = rs.getString("userType");
				user = new User(name,phone_no,email,password,userType);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return user;
	}

	// Select all Users
	public List<User> selectAllUsers() throws SQLException {
		List<User> users = new ArrayList<>();
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String phone_no = rs.getString("phone_no");
				String userType = rs.getString("userType");
				users.add(new User(name,phone_no,email,id,password,userType));
			}

		}
		return users;
	}

	// update user
	public boolean updateUser(User user) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
			System.out.println("UPDATED_USER: " + statement);
			statement.setInt(4, user.getId());
			statement.setString(1, user.getName());
			statement.setString(3, user.getEmail());
			statement.setString(2, user.getPhone_no());
			statement.setString(5, user.getPassword());
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	// delete User
	public boolean deleteUser(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}


	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQL STATE: " + ((SQLException) e).getSQLState());
				System.err.println("ERROR CODE: " + ((SQLException) e).getErrorCode());
				System.err.println("MESSAGE: " + ((SQLException) e).getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause:" + t);
					t = t.getCause();
				}

			}
		}
	}

}
