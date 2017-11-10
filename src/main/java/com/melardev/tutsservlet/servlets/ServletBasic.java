package com.melardev.tutsservlet.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "ServletBasicUsage", urlPatterns = "/basic", initParams = {
		@WebInitParam(name = "name", value = "MyBasicServlet"), @WebInitParam(name = "", value = ""), })
public class ServletBasic extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletBasic() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		HttpSessionBindingListener obj = new HttpSessionBindingListener() {
			public void valueBound(HttpSessionBindingEvent event) {
				System.out.println("OLAO");
			}

			public void valueUnbound(HttpSessionBindingEvent event) {
				System.out.println("AAA");
			}
		};

		PrintWriter out = response.getWriter();

		out.println("<!DOCTYPE html>" + "<html>" + "	<head>" + "   	<meta charset=\"UTF-8\">"
				+ "    	<title>title</title>" + "  </head>" + "	<body>" + "		Really basic stuff isn't ?<br />"
				+ "		<a href='" + getServletContext().getContextPath() + "'>Go Home</a>" + "	</body>" + "</html>");
		String queryArgs = request.getQueryString();
		if (queryArgs != null) {
			System.out.println(queryArgs);
			Enumeration<String> params = request.getParameterNames();
			
			while (params.hasMoreElements()) {
				String key = params.nextElement();
				System.out.println(key + " = " + request.getParameter(key));
			}
			Map<String, String[]> paramsMap = request.getParameterMap();
			System.out.println("Map");
			for (Map.Entry<String, String[]> entry : paramsMap.entrySet()) {
				System.out.println(entry.getKey() + "/" + String.join(",", entry.getValue()));
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		doGet(request, response);
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		Enumeration<String> initParams = config.getInitParameterNames();
		while (initParams.hasMoreElements()) {
			String initParam = initParams.nextElement();
			System.out.println(config.getInitParameter(initParam));
		}
	}

}
