package com.melardev.tutsservlet.listeners;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class ListenerDemo
 */
@WebListener(value = "This is the description")
public class ListenerDemo implements ServletContextListener, ServletContextAttributeListener,
        HttpSessionListener, HttpSessionAttributeListener, HttpSessionActivationListener,
        HttpSessionBindingListener, ServletRequestListener, ServletRequestAttributeListener {

    /**
     * Remainder of scopes
     * HttpSession Class is session object in JSP accessed with sessionScope in EL
     * HttpSession Class is session object in JSP accessed with pageScope in EL
     * ServletContext Class is application object in JSP accessed with applicationScope in EL
     * HttpServletRequest Class is request object in JSP accessed with requestScope in EL
     */

    /**
     * Default constructor.
     */
    public ListenerDemo() {
        System.out.println("Listener initialized");
    }

    /**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("[Listener]: sessionCreated");
        
    }

    /**
     * @see ServletContextAttributeListener#attributeRemoved(ServletContextAttributeEvent)
     */
    public void attributeRemoved(ServletContextAttributeEvent event) {
        System.out.printf("[Listener]: attributeRemoved from ServletContext -> %s : %s\n", event.getName(), event.getValue());
    }

    /**
     * @see ServletRequestAttributeListener#attributeAdded(ServletRequestAttributeEvent)
     */
    public void attributeAdded(ServletRequestAttributeEvent event) {
        System.out.printf("[Listener]: attributeAdded to ServletContext -> %s : %s\n", event.getName(), event.getValue());
    }

    /**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent event) {
        System.out.printf("[Listener]: attributeReplaced within HttpSession -> %s : %s\n", event.getName(), event.getValue());
    }

    /**
     * @see HttpSessionActivationListener#sessionWillPassivate(HttpSessionEvent)
     */
    public void sessionWillPassivate(HttpSessionEvent event) {
        System.out.println("[Listener]: sessionWillPassivate");
    }

    /**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event) {
        // This gets executed when application starts

        System.out.println("[Listener]: contextInitialized" + event.getSource().toString());
        System.out.println(event.getServletContext().getContextPath());
    }

    /**
     * @see ServletContextAttributeListener#attributeAdded(ServletContextAttributeEvent)
     */
    public void attributeAdded(ServletContextAttributeEvent event) {
        System.out.printf("[Listener]: attributeReplaced to ServletContext -> %s : %s\n", event.getName(), event.getValue());
    }

    /**
     * @see ServletRequestListener#requestDestroyed(ServletRequestEvent)
     */
    public void requestDestroyed(ServletRequestEvent event) {
        System.out.println("[Listener]: requestDestroyed " + event.getSource().toString());

    }

    /**
     * @see ServletRequestAttributeListener#attributeRemoved(ServletRequestAttributeEvent)
     */
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
        System.out.println("[Listener Demo]: [Listener Demo]: attributeRemoved");
    }

    /**
     * @see HttpSessionBindingListener#valueBound(HttpSessionBindingEvent)
     */
    public void valueBound(HttpSessionBindingEvent event) {
        System.out.println("[Listener Demo]: [Listener Demo]: valueBound");
    }

    /**
     * @see ServletRequestListener#requestInitialized(ServletRequestEvent)
     */
    public void requestInitialized(ServletRequestEvent sre) {
        sre.getServletRequest().setAttribute("my_attribute", "from_listener");
        System.out.println("[Listener Demo]: [Listener Demo]: requestInitialized");
    }

    /**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("[Listener Demo]: sessionDestroyed");
    }

    /**
     * @see HttpSessionActivationListener#sessionDidActivate(HttpSessionEvent)
     */
    public void sessionDidActivate(HttpSessionEvent se) {
        System.out.println("[Listener Demo]: sessionDidActivate");
    }

    /**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("[Listener Demo]: contextDestroyed");
    }

    /**
     * @see ServletRequestAttributeListener#attributeReplaced(ServletRequestAttributeEvent)
     */
    public void attributeReplaced(ServletRequestAttributeEvent srae) {
        System.out.println("[Listener Demo]: attributeReplaced");
    }

    /**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent event) {
        System.out.println("[Listener Demo]: attributeAdded");
    }

    /**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent event) {
        System.out.println("[Listener Demo]: attributeRemoved");
    }

    /**
     * @see ServletContextAttributeListener#attributeReplaced(ServletContextAttributeEvent)
     */
    public void attributeReplaced(ServletContextAttributeEvent event) {
        System.out.println("[Listener Demo]: attributeReplaced");
    }

    /**
     * @see HttpSessionBindingListener#valueUnbound(HttpSessionBindingEvent)
     */
    public void valueUnbound(HttpSessionBindingEvent event) {
        System.out.println("[Listener Demo]: valueUnbound");
    }

}
