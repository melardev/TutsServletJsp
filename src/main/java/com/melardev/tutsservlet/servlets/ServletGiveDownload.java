package com.melardev.tutsservlet.servlets;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/download")
public class ServletGiveDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletGiveDownload() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		ServletOutputStream os = response.getOutputStream();
		int bytesWritten;
		byte[] buffer = new byte[4096];
		InputStream is = getServletContext().getResourceAsStream("/img/logo.png");
		response.setContentType("image/octet-stream"); //aplication/octet-stream
		response.addHeader("Content-Transfer-Encoding", "Binary");
		response.addHeader("Content-disposition", "attachment; filename='img_logo.jpg'");
		while ((bytesWritten = is.read(buffer, 0, buffer.length)) != -1) {
			os.write(buffer, 0, bytesWritten);
			os.flush();

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		doGet(request, response);
	}

}
