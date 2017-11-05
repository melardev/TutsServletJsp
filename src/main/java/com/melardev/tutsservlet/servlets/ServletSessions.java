package com.melardev.tutsservlet.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletSessions
 */
@WebServlet(value = { "/sessions" })
public class ServletSessions extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletSessions() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		
		HttpSession session = request.getSession();
		
		Object username = session.getAttribute("username");
		if (username != null)
			request.setAttribute("username", username);

		getServletContext().getRequestDispatcher("/sessions.jsp").include(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if ("admin".equals(username) && "admin".equals(password))
			request.getSession(true).setAttribute("username", username);

		doGet(request, response);
	}

}
