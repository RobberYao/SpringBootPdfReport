package com.springBootRedis.demo;

import java.io.Serializable;

public class User implements Serializable {

	private String username;
	private Integer age;

	public User(String username, Integer age) {
		super();
		this.username = username;
		this.age = age;
	}
}
