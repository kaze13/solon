package com.solon.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.solon.dao.spec.INetValueDao;
import com.solon.dto.NetValue;

@Repository
public class NetValueDaoImpl implements INetValueDao {
	private static final NetValueRowMapper ROW_MAPPER = new NetValueRowMapper();

	private static class NetValueRowMapper implements RowMapper<NetValue> {
		@Override
		public NetValue mapRow(ResultSet rs, int arg1) {
			NetValue netValue = new NetValue();
			try {
				netValue.setProductId(rs.getInt("product_id"));
				netValue.setEvalueDate(rs.getDate("evalue_date"));
				netValue.setEvalueType(rs.getInt("evalue_type"));
				netValue.setNetValue(rs.getDouble("net_value"));
				netValue.setNetIncreaseRate(rs.getDouble("net_increase_rate"));
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
			return netValue;
		}
	}

	private static final String SQL_COLUMNS = "product_id, evalue_date, evalue_type, net_value, net_increase_rate";

	private static final String SQL_FIND_ALL = "SELECT " + SQL_COLUMNS
			+ " FROM net_value ";

	private static final String SQL_FIND_BY_PRODUCT_ID = SQL_FIND_ALL
			+ "WHERE PRODUCT_ID = ?";

	private static final String SQL_INSERT = "INSERT INTO net_value ("
			+ SQL_COLUMNS + ") VALUES (?, ?, ?, ?, ?)";

	@Autowired
	private JdbcTemplate template;

	@Override
	public List<NetValue> findAll() {
		return template.query(SQL_FIND_ALL, ROW_MAPPER);
	}

	@Override
	public List<NetValue> findByProductId(int productId) {
		return template.query(SQL_FIND_BY_PRODUCT_ID, ROW_MAPPER, productId);

	}

}
