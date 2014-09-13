package com.solon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solon.dao.spec.IArticleDao;
import com.solon.dto.Article;
import com.solon.service.spec.IArticleService;

@Service
public class ArticleServiceImpl implements IArticleService {

	@Autowired
	private IArticleDao articleDao;
	
	@Override
	public List<Article> findAll() {
		// TODO Auto-generated method stub
		return articleDao.findAll();
	}

	@Override
	public List<Article> findByType(int type) {
		// TODO Auto-generated method stub
		return articleDao.findByType(type);
	}

	@Override
	public Article findById(int id) {
		// TODO Auto-generated method stub
		return articleDao.findById(id);
	}

	@Override
	public void insert(Article article) {
		// TODO Auto-generated method stub
		articleDao.insert(article);
	}

	@Override
	public void remove(int id) {
		// TODO Auto-generated method stub
		articleDao.remove(id);
	}

	@Override
	public void update(Article product) {
		// TODO Auto-generated method stub

	}

}
