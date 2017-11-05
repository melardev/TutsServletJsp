package com.melardev.tutsservlet.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/servlet-to-jsp")
public class ServletToJsp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public ServletToJsp() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		request.setAttribute("title", "ServletToJSP Demo");
		request.setAttribute("message", "Servlet-JSP Communication");
		HttpSession session = request.getSession();
		session.setAttribute("msgSession", "valueFromSession");
		getServletContext().setAttribute("msgAppContext", "valueFromAppContext");
		getServletContext().getRequestDispatcher("/servletToJsp.jsp").forward(request, response);		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		
		
	}

}
