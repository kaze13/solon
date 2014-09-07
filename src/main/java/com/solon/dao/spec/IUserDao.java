package com.solon.dao.spec;

import java.util.List;

import com.solon.dto.User;

public interface IUserDao {

	User findById(String id);

	List<User> findAll();
}
