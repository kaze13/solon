package com.solon.service.spec;

import java.util.List;

import com.solon.dto.User;

public interface IUserService {

	User findById(String id);

	List<User> findAll();

}
