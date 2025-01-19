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

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xadmin.hostelmanagement.bean.Student;
import com.xadmin.hostelmanagement.dao.StudentDao;

@WebServlet("/SearchStudentServlet")
public class SearchStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StudentDao studentDao;

	@Override
	public void init(ServletConfig config) throws ServletException {
		studentDao = new StudentDao();

	}

    private static String jdbcURL = "jdbc:mysql://localhost:3306/Hostel_Management_System";
    private static String jdbcUsername = "root";
    private static String jdbcPassword = "mysql";
    private static String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    private static String SELECT_BY_ID = "SELECT Id, Time_in, Time_out FROM STUDENT WHERE Id = ?";

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Student> records = new ArrayList<>();

            while (resultSet.next()) {
                int id1 = resultSet.getInt("Id");
                Timestamp time_in = resultSet.getTimestamp("Time_in");
                Timestamp time_out = resultSet.getTimestamp("Time_out");

                Student record = new Student(id1, time_in, time_out);
                records.add(record);
            }
            request.setAttribute("records", records);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("TableVeiwId.jsp").forward(request, response);
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Cannot connect to the database", e);
        }
        return connection;
    }
}
