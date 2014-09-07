package com.solon.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.solon.dao.spec.IProductDao;
import com.solon.dto.Product;

@Repository
public class ProductDaoImpl implements IProductDao {
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

	private static final String SQL_FIND_BY_ID = SQL_FIND_ALL
			+ " WHERE PRODUCT_ID = ?";
	private static final String SQL_INSERT = "INSERT INTO product ("
			+ SQL_COLUMNS
			+ ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static String SQL_DELETE = "delete from product where product_id = ? ";

	@Autowired
	private JdbcTemplate template;

	@Override
	public List<Product> findAll() {
		return template.query(SQL_FIND_ALL, ROW_MAPPER);
	}

	@Override
	public Product findById(int id) {
		return template.queryForObject(SQL_FIND_BY_ID, ROW_MAPPER, id);
	}

	@Override
	public void insert(final Product product) {
		template.update(SQL_INSERT, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				new QueryRunner().fillStatement(ps, product.getProductId(),
						product.getProductName(),
						product.getProductShortName(), product.getStatus(),
						product.getStrategy(), product.getStatus(),
						product.getStrategy(), product.getRange(),
						product.getManager(), product.getMinInvest(),
						product.getAdoptionPeriod(), product.getClosePeriod(),
						product.getCreateDate(), product.getOpenDate(),
						product.getWatchingOrg(), product.getTrustee(),
						product.getBank(), product.getBorker(),
						product.getCounselor(), product.getSubscriptionFee(),
						product.getAnnualManageFee(),
						product.getFloatManageFee(),
						product.getSubscriptionAccount(),
						product.getSubscriptionBank(),
						product.getSubscriptionId(),
						product.getSubscriptionProcess());
			}
		});

	}
}
