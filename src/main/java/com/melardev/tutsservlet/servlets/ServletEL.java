package com.melardev.tutsservlet.servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.melardev.tutsservlet.beans.UserEL;


@WebServlet("/el")
public class ServletEL extends HttpServlet {
	private static final long serialVersionUID = 1L;

	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletEL() {
		super();
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		
		HashMap<String, String> sessionMap = new HashMap<String, String>();
		sessionMap.put("user_session", "valueSession");
		
		UserEL user = new UserEL("user_request", "password");
		
		
		// will be accessible in request obj in JSP
		request.setAttribute("user", user); 
		// will be accessible in session obj in JSP
		request.getSession().setAttribute("userSession", sessionMap);
		// will be accessible in application obj in JSP
		request.getServletContext().setAttribute("user", "user in applicationScope");
		getServletContext().getRequestDispatcher("/jspEL.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
