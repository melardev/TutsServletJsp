package com.melardev.tutsservlet.servlets;

import java.io.IOException;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.runtime.HttpJspBase;

import com.melardev.tutsservlet.filters.FilterServlet;

import javafx.scene.control.TreeTableRow;

/**
 * Servlet implementation class ServletFiltered
 * 
 */
//@WebServlet("/servlet-filtered")
public class ServletFiltered extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletFiltered() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		if (true)
			throw new RuntimeException();
		request.setAttribute("my_attribute", "from_servlet");
		getServletContext().getRequestDispatcher("/filters/filter_servlet.jsp").include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

		doGet(request, response);
	}

}
