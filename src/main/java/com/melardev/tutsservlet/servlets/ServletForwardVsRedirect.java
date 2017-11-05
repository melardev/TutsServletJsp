package com.melardev.tutsservlet.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/forward-vs-redirect")
public class ServletForwardVsRedirect extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletForwardVsRedirect() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

		/*
		 * This Demo consists on showing the same Jsp but when you press the button on
		 * that Jsp who takes care of processing the form? the Jsp or Servlet? If you
		 * forward then you don't change the context, the servlet just outputs the
		 * content of the jsp, the url does not change, we are still in this servlet so
		 * the form is processed by the servlet, if you redirect, then we completely
		 * change the context and now we are in the jsp, not in the servlet, the url has
		 * changed and it is the jsp who processes the form
		 */
		if ("redirect".equalsIgnoreCase(request.getParameter("action")))
			response.sendRedirect(getServletContext().getContextPath() + "/forward_vs_redirect.jsp?action=redirect");
		else if ("forward".equalsIgnoreCase(request.getParameter("action")))
			getServletContext().getRequestDispatcher("/forward_vs_redirect.jsp").forward(request, response);
		else
			getServletContext().getRequestDispatcher("/forward_vs_redirect.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		request.setAttribute("title", "Forward vs Redirect");
		getServletContext().getRequestDispatcher("/includes/header.jsp").include(request, response);
		response.getWriter().println("<h3>Servlet Processed the form<h3><br/>"
				+ "<a href='" + getServletContext().getContextPath() + "/forward-vs-redirect'>Go Back</a>");
		getServletContext().getRequestDispatcher("/includes/footer.jsp").include(request, response);
	}

}
