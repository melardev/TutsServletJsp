package com.melardev.tutsservlet.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Application Lifecycle Listener implementation class ListenerApp
 *
 */
public class ListenerApp implements ServletContextListener {

    
    public ListenerApp() {
        // TODO Auto-generated constructor stub
    }

	
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	
    public void contextInitialized(ServletContextEvent sce)  { 
    	// This gets executed when application starts
    	
    }
	
}
