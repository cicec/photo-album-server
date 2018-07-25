package com.cicec.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cicec.dao.UserMapper;
import com.cicec.pojo.User;
import com.cicec.service.UserService;

@Service
public class IUserService implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public List<User> getUserAll() {
		// TODO Auto-generated method stub
		return userMapper.getUserAll();
	}

	@Override
	public List<User> getUserForId(int id) {
		// TODO Auto-generated method stub
		return userMapper.getUserForId(id);
	}
	
	@Override
	public List<User> getUserForName(String name) {
		// TODO Auto-generated method stub
		return userMapper.getUserForName(name);
	}

	@Override
	public int addUser(User user) {
		// TODO Auto-generated method stub
		return userMapper.addUser(user);
	}

	@Override
	public int modifyUser(User user) {
		// TODO Auto-generated method stub
		return userMapper.modifyUser(user);
	}
}
