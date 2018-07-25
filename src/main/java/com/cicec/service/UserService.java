package com.cicec.service;

import java.util.List;

import com.cicec.pojo.User;

public interface UserService {
	public List<User> getUserAll();

	public List<User> getUserForId(int id);

	public List<User> getUserForName(String name);
	
	public int addUser(User user);
	
	public int modifyUser(User user);
}
