package com.solon.dao.spec;

import java.util.List;

import com.solon.dto.Article;

public interface IArticleDao {

	List<Article> findAll();

	List<Article> findByType(int type);

	Article findById(int id);

	void insert(Article product);

	void remove(int id);

	void update(Article product);

}
