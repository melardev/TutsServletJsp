package com.melardev.tutsservlet.servlets;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.tagext.ValidationMessage;


public class ServletMail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ServletMail() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/demos/mail.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 // 1 - get a mail session
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtps.host", "smtp.gmail.com");
        props.put("mail.smtps.port", 465);
        props.put("mail.smtps.auth", "true");
        props.put("mail.smtps.quitwait", "false");
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);

        // 2 - create a message
        Message message = new MimeMessage(session);
        message.setSubject(subject);
        if (bodyIsHTML) {
            message.setContent(body, "text/html");
        } else {
            message.setText(body);
        }

        // 3 - address the message
        Address fromAddress = new InternetAddress(from);
        Address toAddress = new InternetAddress(to);
        message.setFrom(fromAddress);
        message.setRecipient(Message.RecipientType.TO, toAddress);

        // 4 - send the message
        Transport transport = session.getTransport();
        transport.connect("johnsmith@gmail.com", "sesame");
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
        */
		/*
		Session session;
	    // The sender's email address
	     String from;
	    // The subject of the message. 
	     String subject;
	    // The recipient ("To:"), as Strings.
	     List<String> toList = new ArrayList<>();
	   // The CC list, as Strings
	     List<String> ccList = new ArrayList<String>();
	    //The BCC list, as Strings.
	     List<String> bccList = new ArrayList<String>();
	    // The text of the message.
	     String body;
	    // The SMTP relay host
	     String mailHost;
	   
	    boolean verbose;
	    
	    
       // Properties object used to pass props into the MAIL API 
        Properties props = new Properties();
        props.put("mail.smtp.host", mailHost);

        // Create the Session object
        if (session == null) {
            session = Session.getDefaultInstance(props, null);
            if (verbose)
                session.setDebug(true);        // Verbose!
        }

        // create a message
        final Message mesg = new MimeMessage(session);

        InternetAddress[] addresses;

        // TO Address list
        addresses = new InternetAddress[toList.size()];
        for (int i=0; i<addresses.length; i++)
            addresses[i] = new InternetAddress((String)toList.get(i));
        mesg.setRecipients(Message.RecipientType.TO, addresses);

        // From Address
        mesg.setFrom(new InternetAddress(from));

        // CC Address list
        addresses = new InternetAddress[ccList.size()];
        for (int i=0; i<addresses.length; i++)
            addresses[i] = new InternetAddress((String)ccList.get(i));
        mesg.setRecipients(Message.RecipientType.CC, addresses);

        // BCC Address list
        addresses = new InternetAddress[bccList.size()];
        for (int i=0; i<addresses.length; i++)
            addresses[i] = new InternetAddress((String)bccList.get(i));
        
        mesg.setRecipients(Message.RecipientType.BCC, addresses);
        mesg.setSubject(subject);
        mesg.setText(body);
        Transport.send(mesg);
        */
        
	}

}
