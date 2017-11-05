package com.melardev.tutsservlet.beans;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BeanLoginValidator {

	private String username;
	private String password;
	public long timestamp;
	public boolean admin;
	

	public BeanLoginValidator(String username, String password, long timestamp, boolean admin) {
		this.username = username;
		this.password = password;
		this.timestamp = timestamp;
		this.admin = admin;
	}
public BeanLoginValidator() {}
	public boolean isAdmin() {
		return admin;
	}
	
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	public boolean isValidUser(){
	    // harcoded Strings based authentication .... T_T
		if("user".equalsIgnoreCase(username) && "secret".equalsIgnoreCase(password))
			return true;
		else if("admin".equalsIgnoreCase(username) && "admin".equalsIgnoreCase(password)) {
			setAdmin(true);
			return true;
		}else
			return false;
	  }
	
	public String getTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/YY HH:mm:ss");
		String time = sdf.format(new Date(timestamp));
		return time;
		
	}
}
