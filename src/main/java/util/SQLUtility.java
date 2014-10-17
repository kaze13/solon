package util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.jdbc.core.RowMapper;


public class SQLUtility {

	public static String wrapBySingleQuat(String s){
		return "\'" + s + "\'";
	}
	public static String getFullSQLByCondition(Map<String, String> condition, String basicQuery){
		StringBuffer resultSql = new StringBuffer(basicQuery);
		resultSql.append(" where 1=1 ");
		if(condition != null && condition.size() > 0){
			
			
			Set<String> key = condition.keySet();
	        for (Iterator<String> it = key.iterator(); it.hasNext();) {
	            String tKey = (String) it.next();
	            resultSql.append(" and " + tKey + "=" + condition.get(tKey));
	        }
		}
		
		return resultSql.toString();
	}
	
	public static String getPagingSQL(int count, int countPerPage, int page, String Query){
		StringBuffer resultSql = new StringBuffer(Query);
		int start = (page - 1) * countPerPage;
		resultSql.append(" limit " + start + "," + countPerPage);
		return resultSql.toString();
		
	}
	
	public static final CountRowMapper COUNT_ROW_MAPPER = new CountRowMapper();
	public static class CountRowMapper implements RowMapper<Integer> {
		@Override
		public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			try{
				Integer resultCount = rs.getInt(1);
				return resultCount;
			}
			catch(SQLException e){
				e.printStackTrace();
			}
			return -1;
		}
		
	}
	
	
}
