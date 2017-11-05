package com.melardev.tutsservlet.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/cookies")
public class ServletCookie extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletCookie() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		Cookie[] cookies = request.getCookies();
		String color = "#fefefe";
		if (cookies != null) {
			try {
				for (int i = 0; i < cookies.length; i++) {
					Cookie cookie = cookies[i];
					if (cookie.getName().equalsIgnoreCase("color")) {
						color = cookie.getValue();
						break;
					}
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}

		request.setAttribute("color", color);
		request.setAttribute("title", "Cookies Demo");
		showUi(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		String color = "#fefefe";
		try {
			color = request.getParameter("color");
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		Cookie cookie = null;

		//Not needed really we can just skip to new Cookie("color", String.valueOf(color));
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equalsIgnoreCase("color")) {
					c.setValue(String.valueOf(color));
					cookie = c;
					break;
				}
			}
		}
		if (cookie == null) {
			cookie = new Cookie("color", String.valueOf(color));
			cookie.setHttpOnly(true);
			cookie.setComment("this cookie stores the value of the background color");

		}
		cookie.setMaxAge(1 * 60 * 60);
		//cookie.setMaxAge(-1);// will delete the cookie
		response.addCookie(cookie);

		request.setAttribute("color", color);
		request.setAttribute("title", "Cookies Demo After Post");
		showUi(request, response);
	}

	private void showUi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/includes/header.jsp").include(request, response);
		getServletContext().getRequestDispatcher("/cookies.jsp").include(request, response);
		getServletContext().getRequestDispatcher("/includes/footer.jsp").include(request, response);

	}

}
