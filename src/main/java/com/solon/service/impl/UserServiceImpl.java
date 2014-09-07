package com.solon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solon.dao.spec.IUserDao;
import com.solon.dto.User;
import com.solon.service.spec.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	IUserDao userDao;

	@Override
	public User findById(String id) {
		return userDao.findById(id);
	}
	
	@Override
	public List<User> findAll(){
		return userDao.findAll();
	}

}
