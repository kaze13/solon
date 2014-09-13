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
import com.solon.dao.spec.IArticleDao;
import com.solon.dto.Article;
@Repository
public class ArticleDaoImpl implements IArticleDao {

	private static final ArticleRowMapper ROW_MAPPER = new ArticleRowMapper();

	private static class ArticleRowMapper implements RowMapper<Article> {
		@Override
		public Article mapRow(ResultSet rs, int arg1) {
			Article article = new Article();
			try {
				article.setId(rs.getInt("article_id"));
				article.setType(rs.getInt("article_type"));
				article.setContent(rs.getString("content"));
				article.setCreateDate(rs.getDate("publish_date"));
				article.setTitle(rs.getString("title"));

			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
			return article;
		}
	}

	
	private static String INSERT_SQL = " insert into article (article_type, publish_date, title, content) values (?, ?, ?, ?)";
	private static String SQL_SELECT_ALL = "select article_id, article_type, title, content, publish_date from article order by article_id DESC";
	private static String SQL_SELECT_BY_ID = "select article_id, article_type, title, content, publish_date from article where article_id = ? order by article_id DESC";
	private static String SQL_SELECT_BY_TYPE = "select article_id, article_type, title, content, publish_date from article where article_type = ? order by article_id DESC";
	private static String SQL_DELETE_BY_ID = " delete from article where article_id = ?";
	
	
	@Autowired
	private JdbcTemplate template;
	
	@Override
	public List<Article> findAll() {
		// TODO Auto-generated method stub
		return template.query(SQL_SELECT_ALL, ROW_MAPPER);
	}

	@Override
	public List<Article> findByType(int type) {
		// TODO Auto-generated method stub
		return template.query(SQL_SELECT_BY_TYPE, ROW_MAPPER, type);
	}

	@Override
	public Article findById(int id) {
		// TODO Auto-generated method stub
		List<Article> result = template.query(SQL_SELECT_BY_ID, ROW_MAPPER, id);
		if(result.size() > 0){
			return result.get(0);
		}
		return null;
	}

	@Override
	public void insert(final Article article) {
		// TODO Auto-generated method stub
		template.update(INSERT_SQL, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				new QueryRunner().fillStatement(ps, article.getType(),
						article.getCreateDate(), article.getTitle(), article.getContent());
			}
		});
	}

	@Override
	public void remove(int id) {
		// TODO Auto-generated method stub
		template.update(SQL_DELETE_BY_ID, id);
	}

	@Override
	public void update(Article product) {
		// TODO Auto-generated method stub

	}

}
