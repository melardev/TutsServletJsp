package com.melardev.tutsservlet.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * You have to use Servlet 3.0 because the version 2.X are synchronous, whereas
 * 3.0 is asynch
 */
@WebServlet(urlPatterns = "/life_cycle", initParams = @WebInitParam(name = "name", value = "Servlet Life cycle guy"))
public class ServletLifeCycle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletLifeCycle() {
		super();
		System.out.println("Constructor called");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		// Called when we have a GET HTTP request
		if (request.getParameter("exit") != null) {
			throw new RuntimeException();
		}

		System.out.println("doGet() called");
		PrintWriter out = response.getWriter();
		request.setAttribute("title", "LifeCycle Demo");
		getServletContext().getRequestDispatcher("/includes/header.jsp").include(request, response);
		out.println("Studying the Servlet's Lifecycle !!!");
		getServletContext().getRequestDispatcher("/includes/footer.jsp").include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	@Override
	public void destroy() {
		// Here we want to close database connections, opened files, and make some cleanup afterwards
		System.out.println("destroy() called");
		super.destroy();
	}

	@Override
	public void init() throws ServletException {
		super.init();
		// Called once, not called anymore in the subsequent requests, meant to make initialization
		// Executed when the Servlet is created, this means, when the user requests the URL or
		// when the server starts if load-on-startup element is present

		String name = getServletConfig().getInitParameter("name");
		System.out.println("init() called, it is time to meet me, my name is " + name);
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		System.out.println("service() called");
		// called by the web container to handle the request coming from the client
		// doGet, doPost and so on are called from here, put a breakpoint on doGet and look at the stacktrace
		super.service(req, res);
	}

}
