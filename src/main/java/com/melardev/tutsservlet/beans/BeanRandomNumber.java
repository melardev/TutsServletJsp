package com.melardev.tutsservlet.beans;

import java.util.Random;

public class BeanRandomNumber {

	private Random random;
	String time;
	public BeanRandomNumber() {
		random = new Random();
	}
	public int getRandomNumber() {
		return random.nextInt(100000);
	}
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
