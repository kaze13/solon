package com.solon.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solon.dao.spec.IProductDao;
import com.solon.dto.Article;
import com.solon.dto.Product;
import com.solon.service.spec.IProductService;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IProductDao productDao;

	@Override
	public List<Product> findAll() {
		return productDao.findAll();
	}

	@Override
	public Product findById(int id) {
		return productDao.findById(id);
	}

	@Override
	public void insert(Product product) {
		productDao.insert(product);
	}
	
	@Override
	public void remove(int id){
		productDao.remove(id);
	}
	
	@Override
	public void update(Product product){
		productDao.update(product);
	}

	@Override
	public int getProductsPagesCount(Map<String, String> condition) {
		// TODO Auto-generated method stub
		return productDao.getProductsPagesCount(condition);
	}

	@Override
	public List<Product> getProductsByPaging(Map<String, String> condition,
			int page) {
		// TODO Auto-generated method stub
		return productDao.getProductsByPaging(condition, page);
	}
}
