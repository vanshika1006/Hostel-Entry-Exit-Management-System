package com.xadmin.hostelmanagement.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xadmin.hostelmanagement.bean.User;
import com.xadmin.hostelmanagement.dao.UserDao;

/**
 * Servlet implementation class NewRegisteration2
 */
@WebServlet("/")
public class NewRegisteration2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;

	@Override
	public void init(ServletConfig config) throws ServletException {
		userDao = new UserDao();

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.print("here 888");
		switch (action) {
		case "/new":
			showNewForm(request, response);
			break;
		case "/insert":
			try {
				insertUser(request, response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}
		private void showNewForm(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {

			RequestDispatcher dispatcher = request.getRequestDispatcher("new-registration-form.jsp");
			dispatcher.forward(request, response);
		}

		private void insertUser(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
			String name = request.getParameter("name");
			String phone_no = request.getParameter("phone_no");
			String email = request.getParameter("email");
			int id = Integer.parseInt(request.getParameter("id"));
			String password = request.getParameter("password");
			String userType = request.getParameter("userType");
			User newUser = new User(name, phone_no , email ,id, password,userType);
			userDao.insertUser(newUser);
			response.sendRedirect("First_Page.jsp");
		}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				doGet(request, response);
	}

}
