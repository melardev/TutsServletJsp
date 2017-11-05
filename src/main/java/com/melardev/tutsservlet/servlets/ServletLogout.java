package com.melardev.tutsservlet.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class ServletLogout extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if (req.getSession() != null)
			req.getSession().invalidate();

		resp.sendRedirect(getServletContext().getContextPath() + "/sessions");

	}

}
