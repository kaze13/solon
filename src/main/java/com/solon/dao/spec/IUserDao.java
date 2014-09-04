package com.solon.dao.spec;

import com.solon.dto.User;

public interface IUserDao {

	User findById(String id);
}
