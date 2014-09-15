package com.solon.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import util.SQLUtility;

import com.solon.dao.spec.IProductDao;
import com.solon.dto.Article;
import com.solon.dto.Product;

@Repository
public class ProductDaoImpl implements IProductDao {
	private static final ProductRowMapper ROW_MAPPER = new ProductRowMapper();

	private static class ProductRowMapper implements RowMapper<Product> {
		@Override
		public Product mapRow(ResultSet rs, int arg1) {
			Product product = new Product();
			try {
				product.setProductId(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setProductShortName(rs.getString(3));
				product.setStatus(rs.getInt(4));
				product.setStrategy(rs.getInt(5));
				product.setRange(rs.getString(6));
				product.setManager(rs.getString(7));
				product.setMinInvest(rs.getString(8));
				product.setAdoptionPeriod(rs.getString(9));
				product.setClosePeriod(rs.getString(10));
				product.setCreateDate(rs.getDate(11));
				product.setOpenDate(rs.getString(12));
				product.setWatchingOrg(rs.getString(13));
				product.setTrustee(rs.getString(14));
				product.setBank(rs.getString(15));
				product.setBorker(rs.getString(16));
				product.setCounselor(rs.getString(17));
				product.setSubscriptionFee(rs.getDouble(18));
				product.setAnnualManageFee(rs.getDouble(19));
				product.setFloatManageFee(rs.getDouble(20));
				product.setSubscriptionAccount(rs.getString(21));
				product.setSubscriptionBank(rs.getString(22));
				product.setSubscriptionId(rs.getString(23));
				product.setSubscriptionProcess(rs.getString(24));

			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException();
			} catch (NullPointerException e) {
				// do nothing
			}
			return product;
		}
	}

	private static final ProductSimpleRowMapper SIMPLE_ROW_MAPPER = new ProductSimpleRowMapper();

	private static class ProductSimpleRowMapper implements RowMapper<Product> {

		@Override
		public Product mapRow(ResultSet rs, int rowNum) {
			// TODO Auto-generated method stub
			Product product = new Product();
			try {
				product.setProductId(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setProductShortName(rs.getString(3));
				product.setStatus(rs.getInt(4));
				product.setStrategy(rs.getInt(5));
				product.setBuyUrl(rs.getString(6));
				product.setNewestNetVal(rs.getDouble(7));
				product.setTotalNetVal(rs.getDouble(8));
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
			return product;
		}
		
		
	}
	private static final String SQL_COLUMNS = "product_name, product_short_name, status, strategy, product_range, manager, min_invest,"
			+ "adoption_period, close_period, create_date, open_date, watching_org, trustee, bank, broker, counselor, subscription_free, anual_manage_free, "
			+ "float_manage_free, subscription_account, subscription_bank, subscription_id, subscription_process";
	
	private static final String SQL_SIMPLE_COLS = "product.product_name, product.product_short_name, product.status, product.strategy, product.buy_url, "
			+ "net_value.net_value, net_value.net_increase_rate ";
	private static final String SQL_FIND_ALL = "SELECT " + "product_id,"
			+ SQL_COLUMNS + " FROM product ";

	private static final String SQL_SIMPLE_FIND_ALL = "SELECT " +  " product.product_id, " + SQL_SIMPLE_COLS + " FROM product, net_value ";
	private static final String SQL_PRODUCT_LIST_QUERY_CONDITION = " and product.product_id = net_value.product_id "
			+ " and net_value.evalue_date = ( select max(evalue_date) from net_value nv where nv.product_id = product.product_id)";
	
	private static final String SQL_FIND_BY_ID = SQL_FIND_ALL
			+ " WHERE PRODUCT_ID = ?";
	private static final String SQL_INSERT = "INSERT INTO product ("
			+ SQL_COLUMNS
			+ ") VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String SQL_ORDER_BY_STRING =" order by id ";
	private static final String SQL_UPDATE = "UPDATE solon.product SET "
			+ "product_name = ?, product_short_name = ?, status = ?, "
			+ "strategy = ?, product_range = ?, manager = ?, min_invest = ?, "
			+ "adoption_period = ?, close_period = ?, create_date = ?, open_date = ?,"
			+ " watching_org = ?, trustee = ?, bank = ?, broker = ?, counselor = ?,"
			+ " subscription_free = ?, anual_manage_free = ?, float_manage_free = ?,"
			+ " subscription_account = ?, subscription_bank = ?, subscription_id = ?, "
			+ "subscription_process = ? WHERE product_id = ? ";

	private static String SQL_DELETE = "delete from product where product_id = ? ";

	private static String SQL_BASIC_COUNT_STRING = " select count(*) from product ";
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
	public void remove(int id) {
		template.update(SQL_DELETE, id);
	}

	@Override
	public void update(final Product product) {
		template.update(SQL_UPDATE, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				new QueryRunner().fillStatement(ps, product.getProductName(),
						product.getProductShortName(), product.getStatus(),
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
						product.getSubscriptionProcess(),
						product.getProductId());
			}
		});
	}

	@Override
	public void insert(final Product product) {
		template.update(SQL_INSERT, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				new QueryRunner().fillStatement(ps, product.getProductName(),
						product.getProductShortName(), product.getStatus(),
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

	
	private static final int NUMBER_PER_PAGE = 2;

	
	@Override
	public int getProductsPagesCount(Map<String, String> condition) {
		// TODO Auto-generated method stub
		String SQL_COUNT_STRING = SQLUtility.getFullSQLByCondition(condition, SQL_BASIC_COUNT_STRING);
		List<Integer> result = template.query(SQL_COUNT_STRING, SQLUtility.COUNT_ROW_MAPPER);
		return (int) Math.ceil(result.get(0) / (double)NUMBER_PER_PAGE) ;
	}

	@Override
	public List<Product> getProductsByPaging(Map<String, String> condition,
			int page) {
		// TODO Auto-generated method stub
		String queryString = SQLUtility.getFullSQLByCondition(condition, SQL_SIMPLE_FIND_ALL);
		int totalCount = getProductsPagesCount(condition);
		queryString += SQL_PRODUCT_LIST_QUERY_CONDITION;
		queryString = SQLUtility.getPagingSQL(totalCount, NUMBER_PER_PAGE, page, queryString + SQL_ORDER_BY_STRING);
		List<Product> result = template.query(queryString, SIMPLE_ROW_MAPPER);
		return result;
	}
}
