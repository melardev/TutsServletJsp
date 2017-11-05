package com.melardev.tutsservlet.servlets;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.MultipartStream;

/**
 * Servlet implementation class ServletUpload
 */
@WebServlet(name = "ServletUpload", displayName = "servletUpload", urlPatterns = { "/upload" })
@MultipartConfig //this servlet will handle multipart/form-data
public class ServletUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletUpload() {
		super();
	}

	private String webTempPath;

	public void init() {
		webTempPath = getServletContext().getRealPath("/") + "uploads";

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

		getServletContext().getRequestDispatcher("/upload.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			java.io.IOException {
		System.out.println(webTempPath);

		Part part = request.getPart("file");
		InputStream is = part.getInputStream();
		BufferedInputStream bin = new BufferedInputStream(is);
		FileOutputStream fos = new FileOutputStream(new File(webTempPath + File.separator + "filename"));
		int ch = 0;
		while ((ch = bin.read()) != -1) {
			fos.write(ch);
		}
		fos.flush();
		fos.close();
		bin.close();

		getServletContext().getRequestDispatcher("/success.jsp").forward(request, response);

	}
}
