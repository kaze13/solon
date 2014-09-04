package com.solon.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.solon.dto.Product;

public class ProductDaoImpl {
	private static final ProductRowMapper ROW_MAPPER = new ProductRowMapper();

	private static class ProductRowMapper implements RowMapper<Product> {
		@Override
		public Product mapRow(ResultSet resultSet, int arg1) {
			Product productDto;
			try {
				productDto = new Product(resultSet.getInt(1),
						resultSet.getString(2), resultSet.getString(3),
						resultSet.getInt(4), resultSet.getInt(5),
						resultSet.getString(6), resultSet.getString(7),
						resultSet.getString(8), resultSet.getString(9),
						resultSet.getString(10), resultSet.getDate(11),
						resultSet.getString(12), resultSet.getString(13),
						resultSet.getString(14), resultSet.getString(15),
						resultSet.getString(16), resultSet.getString(17),
						resultSet.getDouble(18), resultSet.getDouble(19),
						resultSet.getDouble(20), resultSet.getString(21),
						resultSet.getString(22), resultSet.getString(23),
						resultSet.getString(24));
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
			return productDto;
		}
	}

	private static final String SQL_COLUMNS = "product_id, product_name, product_short_name, status, strategy, product_range, manager, min_invest,"
			+ "adoption_period, close_period, create_date, open_date, watching_org, trustee, bank, broker, counselor, subscription_free, anual_manage_free, "
			+ "float_manage_free, subscription_account, subscription_bank, subscription_id, subscription_process";

	private static final String SQL_FIND_ALL = "SELECT " + SQL_COLUMNS
			+ " FROM product ";

	private static final String SQL_INSERT = "INSERT INTO product ("
			+ SQL_COLUMNS
			+ ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static String SQL_DELETE = "delete from product where product_id = ? ";

	@Autowired
	private JdbcTemplate template;

	public List<Product> findAll() {
		return template.query(SQL_FIND_ALL, ROW_MAPPER);
	}

	// @Override
	// public void insert(final List<Product> productList) {
	// template.batchUpdate(SQL_INSERT, new BatchPreparedStatementSetter() {
	//
	// @Override
	// public void setValues(PreparedStatement ps, int i) throws SQLException {
	// Product obj = productList.get(i);
	// Long seq = template.queryForObject(SQL_NEXT_SEQ, Long.class);
	//
	// new QueryRunner().fillStatement(ps, seq, obj.getObjId(),
	// obj.getHasDraft(),
	// Integer.valueOf(obj.getDraftType().ordinal()), obj.getDraftId(),
	// obj.getSheetId());
	//
	// obj.setId(seq);
	// }
	//
	// @Override
	// public int getBatchSize() {
	// return objList.size();
	// }
	// });
	//
	// return OOMDaoUtil.sumIntArray(rets);
	// }
}
