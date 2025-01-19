package com.xadmin.hostelmanagement.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xadmin.hostelmanagement.bean.Student;
import com.xadmin.hostelmanagement.dao.StudentDao;


@WebServlet("/studentRecords")
public class studentRecords extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String jdbcURL ="jdbc:mysql://localhost:3306/Hostel_Management_System";
	private String jdbcUsername ="root";
	private String jdbcPassword ="mysql";
	private String jdbcDriver ="com.mysql.cj.jdbc.Driver";
	//private static String SELECT_USER_BY_ID_PASSWORD = "SELECT USER FROM NEWREGISTERATION WHERE ID = ? AND PASSWORD = ?;";
	//private static String SELECT_USER_BY_ID = "SELECT NAME,PHONE_NO,EMAIL,PASSWORD,USERTYPE FROM NEWREGISTERATION WHERE ID = ?;";
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
	private StudentDao studentDao;

	@Override
	public void init(ServletConfig config) throws ServletException {
		studentDao = new StudentDao();

	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Student> records = retrieveRecords(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("records", records);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Student-Records.jsp");
        dispatcher.forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private List<Student> retrieveRecords(int id) {
        List<Student> records = new ArrayList<>();
        String sql = "SELECT id, time_in, time_out FROM STUDENT WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id1 = resultSet.getInt("id");
                Timestamp timeIn = resultSet.getTimestamp("time_in");
                Timestamp timeOut = resultSet.getTimestamp("time_out");

                Student record = new Student(id1, timeIn, timeOut);
                records.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly in your application
        }

        return records;
    }

}
