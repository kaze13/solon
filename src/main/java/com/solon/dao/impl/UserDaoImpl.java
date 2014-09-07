package com.solon.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.solon.dao.spec.IUserDao;
import com.solon.dto.User;

@Repository
public class UserDaoImpl implements IUserDao {

	private static final UserRowMapper ROW_MAPPER = new UserRowMapper();

	private static class UserRowMapper implements RowMapper<User> {
		@Override
		public User mapRow(ResultSet rs, int arg1) {
			User user = new User();
			try {
				user.setId(rs.getString("user_id"));
				user.setPassword(rs.getString("user_pw"));

			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
			return user;
		}
	}

	private static final String SQL_COLUMNS = "user_id, user_pw";

	private static final String SQL_FIND_ALL = "SELECT " + SQL_COLUMNS
			+ " FROM users ";

	private static final String SQL_FIND_BY_ID = SQL_FIND_ALL
			+ " WHERE USER_ID = ?";
	private static final String SQL_INSERT = "INSERT INTO product ("
			+ SQL_COLUMNS + ") VALUES (?, ?)";

	@Autowired
	private JdbcTemplate template;

	@Override
	public User findById(String id) {
		return template.queryForObject(SQL_FIND_BY_ID, ROW_MAPPER, id);
	}
	
	@Override
	public List<User> findAll(){
		return template.query(SQL_FIND_ALL, ROW_MAPPER);
	}

}
