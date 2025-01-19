package com.xadmin.hostelmanagement.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xadmin.hostelmanagement.bean.User;
import com.xadmin.hostelmanagement.dao.LoginDao;

@WebServlet("/login")
public class LoginPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String jdbcURL ="jdbc:mysql://localhost:3306/Hostel_Management_System";
	private String jdbcUsername ="root";
	private String jdbcPassword ="mysql";
	private String jdbcDriver ="com.mysql.cj.jdbc.Driver";
	//private static final String SELECT_USER_BY_ID_PASSWORD = "SELECT USER FROM NEWREGISTERATION WHERE ID = ? AND PASSWORD = ?;";
	private static final String SELECT_USER_BY_ID = "SELECT NAME,PHONE_NO,EMAIL,PASSWORD,USERTYPE FROM NEWREGISTERATION WHERE ID = ?;";
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
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher //
		= this.getServletContext().getRequestDispatcher("second-page-student.jsp");

     dispatcher.forward(request, response);

	}

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
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    HttpSession session = request.getSession();
	    int id=Integer.parseInt(request.getParameter("id"));
	    String pwd=request.getParameter("password");
	    User user = selectUser(id);
	    if(LoginDao.validate(id, pwd)){
	    	session.setAttribute("id", id);
	 	    session.setAttribute("user", user);

	 	    if(user.userType.equals("S")) {
	 	    	RequestDispatcher rd=request.getRequestDispatcher("second-page-student.jsp");
		        rd.forward(request,response);
	 	    }else if(user.userType.equals("T")) {
	 	    	RequestDispatcher rd=request.getRequestDispatcher("second-page-teacher.jsp");
		        rd.forward(request,response);
	 	    }

	    }
	    else{
	    	request.setAttribute("errorMessage", "Sorry username or password error");
	        //out.print("");
	        RequestDispatcher rd=request.getRequestDispatcher("First_Page.jsp");
	        rd.include(request,response);
	    }

	    out.close();

	}

}
