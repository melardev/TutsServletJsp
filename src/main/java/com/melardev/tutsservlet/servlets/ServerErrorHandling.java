package com.melardev.tutsservlet.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServerErrorHandling
 */
@WebServlet("/Error")
public class ServerErrorHandling extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServerErrorHandling() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

		System.out.println(request.getDispatcherType());
		Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
		Class errClass = (Class) request.getAttribute("javax.servlet.error.exception_type");
		String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
		String message = (String) request.getAttribute("javax.servlet.error.message");
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");

		StringBuilder errors = new StringBuilder();
		if (servletName != null)
			errors.append("ServletName: " + servletName + "<br>");
		if (statusCode != null)
			errors.append("StatusCode: " + statusCode+ "<br>");

		if (requestUri != null)
			errors.append("requestUri: " + requestUri + "<br />");

		if (message != null)
			errors.append("message: " + message + "<br>");

		String messageError = errors.toString();
		request.setAttribute("errors", messageError);

		if (response.getStatus() == HttpServletResponse.SC_NOT_FOUND)
			getServletContext().getRequestDispatcher("/404.jsp").forward(request, response);
		else if (response.getStatus() >= HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
			getServletContext().getRequestDispatcher("/errors.jsp").forward(request, response);

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
