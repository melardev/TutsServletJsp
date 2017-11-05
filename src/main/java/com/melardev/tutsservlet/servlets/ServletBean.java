package com.melardev.tutsservlet.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.melardev.tutsservlet.beans.BeanUser;


@WebServlet("/beans")
public class ServletBean extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletBean() {
		super();
	
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		
		BeanUser userRequest = new BeanUser("melardevFromRequest");
		BeanUser userSession = new BeanUser("melardevFromSession");
		BeanUser userServletContext = new BeanUser("melardevServletContext");
		
		request.setAttribute("user", userRequest);
		request.getSession().setAttribute("user", userSession);
		getServletContext().setAttribute("user", userServletContext);
		
		request.setAttribute("userbean", userRequest);
		request.getSession().setAttribute("userbean", userSession);
		getServletContext().setAttribute("userbean", userServletContext);
		
		getServletContext().getRequestDispatcher("/beans.jsp").forward(request, response);
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		doGet(request, response);
	}

}
