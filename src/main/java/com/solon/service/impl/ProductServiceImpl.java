package com.solon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solon.dao.spec.IProductDao;
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

}
