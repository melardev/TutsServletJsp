package com.melardev.tutsservlet.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletFromJsp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ServletFromJsp() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Attribute received from JSP using HttpServletRequest Object: " + request.getAttribute(
				"requestVar"));
		System.out.println("Attribute received from JSP using HttpSession Object: " + request.getSession().getAttribute(
				"sessionVar"));
		System.out.println("Attribute received from JSP using ServletContext Object: " + getServletContext().getAttribute("appVar"));
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}
