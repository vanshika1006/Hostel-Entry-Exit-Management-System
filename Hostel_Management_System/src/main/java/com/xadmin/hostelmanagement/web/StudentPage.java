package com.xadmin.hostelmanagement.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xadmin.hostelmanagement.bean.Student;

@WebServlet("/EntryExitServlet")
public class StudentPage extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static String jdbcURL ="jdbc:mysql://localhost:3306/Hostel_Management_System";
	private static String jdbcUsername ="root";
	private static String jdbcPassword ="mysql";
	private static String jdbcDriver ="com.mysql.cj.jdbc.Driver";
	private static String UPDATE_STATUS = "UPDATE newregisteration SET current_status = ? WHERE id = ?;";
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
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
        int id = Integer.parseInt(request.getParameter("id"));
		Student student = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STATUS);){
			if ("entry".equals(action)) {
                updateStatus(id, true);
                recordEntryExitTime(id, true);
            } else if ("exit".equals(action)) {
                updateStatus(id, false);
                recordEntryExitTime(id, false);
            }
            response.sendRedirect("confirmation.jsp");
		}catch (SQLException e) {
			printSQLException(e);
		}



}
	public static void updateStatus(int id , boolean status) {
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_STATUS);){
			statement.setBoolean(1,status);
			statement.setInt(2,id);
			statement.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void recordEntryExitTime(int id, boolean isEntry) throws SQLException {
        String sql = "INSERT INTO Student (id, Time_in) VALUES (?, ?)";
        if (!isEntry) {
            sql = "UPDATE Student SET Time_out = CURRENT_TIMESTAMP WHERE id = ? AND Time_out IS NULL";
        }

        try (Connection connection = getConnection();
        		PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            if (isEntry) {
                pstmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            }
            pstmt.executeUpdate();
        }
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

