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

@WebServlet("/ViewAllStudentsServlet")
public class ViewAllStudentsServlet extends HttpServlet {
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
    private static String SELECT_BY_DATE = "SELECT id, Time_in, Time_out FROM STUDENT WHERE DATE(Time_in) = ?";

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String date = request.getParameter("date");

        List<Student> students = new ArrayList<>();

        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_DATE)) {
            preparedStatement.setString(1, date);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Student student;
                int id1 = resultSet.getInt("id");
                Timestamp timeIn = resultSet.getTimestamp("time_in");
                Timestamp timeOut = resultSet.getTimestamp("time_out");

                Student record = new Student(id1, timeIn, timeOut);
                students.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("records", students);

        // Forward the request to the GetByDate.jsp page
        request.getRequestDispatcher("TableVeiwDate.jsp").forward(request, response);
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
