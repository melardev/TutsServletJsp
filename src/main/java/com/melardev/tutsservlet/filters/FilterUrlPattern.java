package com.melardev.tutsservlet.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/*
@WebFilter(displayName="FilterUrlPattern", filterName="FilterUrlPattern", urlPatterns= {"/filters/url_based/*"},
dispatcherTypes= {DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE, DispatcherType.ERROR},
initParams= {
		@WebInitParam(name="filter-name", value="Generic-Logging-Filter")
})*/
public class FilterUrlPattern implements Filter {

    private String filterName;

    /**
     * Default constructor.
     */
    public FilterUrlPattern() {
        // TODO Auto-generated constructor stub

    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        System.out.println(filterName + " is being destroyed!");
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        //request.getServletContext().log("Log whatever you want");
        System.out.println(request.getDispatcherType());
        // Change the request and the response as you want
        request.setAttribute("filter_attribute", "filter_value");
        System.out.println("doFilter() called, you can open a file and write to it, as if this was a Logger filter");
        // pass the request along the filter chain
        chain.doFilter(request, response);
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        filterName = fConfig.getInitParameter("filter-name");
        System.out.println(filterName + " is being initialized, he begins filtering !");
    }

}
