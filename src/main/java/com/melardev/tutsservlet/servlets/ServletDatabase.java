package com.melardev.tutsservlet.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.melardev.tutsservlet.beans.User;
import com.melardev.tutsservlet.daos.UserDAO;

@WebServlet("/db")
public class ServletDatabase extends HttpServlet {

	@Override
	public void init() throws ServletException {
		super.init();

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		String action = request.getParameter("action");
		if (action == null)
			action = "list"; //default action list users

		ArrayList<User> users = null;
		if (action.equals("list")) {
			try {
				users = UserDAO.getUsers();
				request.setAttribute("users", users);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		getServletContext().getRequestDispatcher("/db_demo.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String userName = request.getParameter("username");

		if (firstName != null && !firstName.isEmpty() && lastName != null && !lastName.isEmpty() && userName != null
				&& !userName.isEmpty()) {
			try {
				User user = new User(firstName, lastName, userName);
				int userId = UserDAO.addUser(user);
				if (userId == -1)//failed
					request.setAttribute("errors", "something went wrong!");

				response.sendRedirect(getServletContext().getContextPath() + "/db");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			request.setAttribute("errors", "please fill the form");

			getServletContext().getRequestDispatcher("/db_demo.jsp").forward(request, response);
		}

	}

}
