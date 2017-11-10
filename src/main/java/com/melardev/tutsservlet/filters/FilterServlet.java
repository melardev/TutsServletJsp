package com.melardev.tutsservlet.filters;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 *
 * One of the advantages of using Filters is decoupling the components, in other words we can create some kind
 * of plugins which some business logic code that is not tied to a servlet or other component, so it can be
 * reused easily.
 * Filters may be used for logging, File processing(Compress files to make them smaller, scaling images, etc)
 * authentication, authorization, redirect user based on Locale 
 * filtering(xss for example),
 *
 *
 */

//@WebFilter(servletNames= {"ServletFiltered"}, dispatcherTypes= {DispatcherType.REQUEST}, filterName="FilterServlet")
public class FilterServlet implements Filter {

	public FilterServlet() {

	}

	public void destroy() {
		System.out.println("FilterServlet is being destroyed!");
	}

	//https://stackoverflow.com/questions/701681/how-can-i-read-an-httpservletreponses-output-stream
	static class MyHttpServletResponse extends HttpServletResponseWrapper {
		private StringWriter sw = new StringWriter();

		public MyHttpServletResponse(HttpServletResponse response) {
			super(response);
		}

		@Override
		public PrintWriter getWriter() throws IOException {
			return new PrintWriter(sw);
		}

		@Override
		public String toString() {
			return sw.toString();
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		// Create wrappers around the request and response, this is the so called decorator design pattern
		// this is meant to help extensibility, which is what we are doing with MyHttpServletResponse
		HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper((HttpServletRequest) request);
		HttpServletResponseWrapper responseWrapper = new MyHttpServletResponse((HttpServletResponse) response);
		
		// pass the request along the filter chain
		System.out.println(request.getDispatcherType());
		chain.doFilter(requestWrapper, responseWrapper);

		//String oldContent = new PrintWriter(responseWrapper.getOutputStream()).toString();
		String oldContent = responseWrapper.toString();
		String newContent = oldContent.replaceAll("servlet", "filter");
		responseWrapper.resetBuffer();
		responseWrapper.getResponse().getWriter().println(newContent);

		//Example: You can implement some authorization checking and send and error for example:
		//responseWrapper.sendError(HttpServletResponse.SC_FORBIDDEN);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("Initializing " + fConfig.getInitParameter("name"));
	}

}
