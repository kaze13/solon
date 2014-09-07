package com.solon.dao.spec;

import java.util.List;

import com.solon.dto.Product;

public interface IProductDao {

	public List<Product> findAll();

	Product findById(int id);

	void insert(Product product);

}
