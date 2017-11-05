package com.melardev.tutsservlet.filters;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

@WebFilter(urlPatterns = "/Error", dispatcherTypes = DispatcherType.ERROR, initParams = @WebInitParam(name = "name", value = "FilterErrorLogger"))
public class FilterErrorLogger implements Filter {

	String name;

	public void init(FilterConfig filterConfig) throws ServletException {
		name = filterConfig.getInitParameter("name");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		System.out.println(name + "::doFilter()");
		chain.doFilter(request, response); // if not called you are not forwarding the response so the user will receive nothing ...
	}

	public void destroy() {
		System.out.println(name + " gets destroy()ed");
	}

	
}
