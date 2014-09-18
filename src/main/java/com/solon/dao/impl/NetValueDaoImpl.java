package com.solon.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
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
				netValue.setId(rs.getInt("id"));
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
	
	private static final String SQL_ORDER_BY = " order by evalue_date DESC ";

	private static final String SQL_COLUMNS = "product_id, evalue_date, evalue_type, net_value, net_increase_rate";

	private static final String SQL_FIND_ALL = "SELECT " + "id, " + SQL_COLUMNS
			+ " FROM net_value ";

	private static final String SQL_FIND_BY_PRODUCT_ID = SQL_FIND_ALL
			+ "WHERE PRODUCT_ID = ? " + SQL_ORDER_BY;

	private static final String SQL_INSERT = "INSERT INTO net_value ("
			+ SQL_COLUMNS + ") VALUES (?, ?, ?, ?, ?)";

	private static final String SQL_UPDATE = "UPDATE solon.net_value SET product_id = ?, evalue_date = ?, "
			+ "evalue_type = ?, net_value = ?, net_increase_rate =?  WHERE id=?";

	private static final String SQL_DELETE = "DELETE FROM net_value WHERE id=?";
	
	private static final String SQL_DELTE_BY_PRODUCT = " DELETE FROM net_value WHERE product_id = ? ";
	
	
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

	
	@Override
	public void insert(final NetValue value) {
		
		template.update(SQL_INSERT, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				new QueryRunner().fillStatement(ps, value.getProductId(),
						value.getEvalueDate(), value.getEvalueType(),
						value.getNetValue(), value.getNetIncreaseRate());
			}
		});
	}

	@Override
	public void update(final NetValue value) {
		template.update(SQL_INSERT, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				new QueryRunner().fillStatement(ps, value.getProductId(),
						value.getEvalueDate(), value.getEvalueType(),
						value.getNetValue(), value.getNetIncreaseRate(), value.getId());
			}
		});
	}
	
	@Override
	public void remove(int id){
		template.update(SQL_DELETE, id);
	}

	@Override
	public void removeByProduct(int pId) {
		// TODO Auto-generated method stub
		template.update(SQL_DELTE_BY_PRODUCT, pId);
	}

	@Override
	public void insertByBatch(final List<NetValue> values) {
		// TODO Auto-generated method stub
		
		template.batchUpdate(SQL_INSERT, new BatchPreparedStatementSetter(){

			@Override
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				// TODO Auto-generated method stub
				NetValue value = values.get(i);
				new QueryRunner().fillStatement(ps, value.getProductId(),
						value.getEvalueDate(), value.getEvalueType(),
						value.getNetValue(), value.getNetIncreaseRate());
			}

			@Override
			public int getBatchSize() {
				// TODO Auto-generated method stub
				return values.size();
			}});
	}

	@Override
	public void updateByBatch(final List<NetValue> values) {
		// TODO Auto-generated method stub
		template.batchUpdate(SQL_UPDATE,  new BatchPreparedStatementSetter(){

			@Override
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				// TODO Auto-generated method stub
				NetValue value = values.get(i);
				new QueryRunner().fillStatement(ps, value.getProductId(),
						value.getEvalueDate(), value.getEvalueType(),
						value.getNetValue(), value.getNetIncreaseRate(), value.getId());
			}

			@Override
			public int getBatchSize() {
				// TODO Auto-generated method stub
				return values.size();
			}});
		
	}

	@Override
	public void deleteByBatch(final List<NetValue> values) {
		// TODO Auto-generated method stub
		template.batchUpdate(SQL_DELETE, new BatchPreparedStatementSetter(){

			@Override
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				// TODO Auto-generated method stub
				NetValue value = values.get(i);
				new QueryRunner().fillStatement(ps, value.getId());
			}

			@Override
			public int getBatchSize() {
				// TODO Auto-generated method stub
				return values.size();
			}
			
		});
	}

}
