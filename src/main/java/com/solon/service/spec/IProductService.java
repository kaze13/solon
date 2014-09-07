package com.solon.service.spec;

import java.util.List;

import com.solon.dto.Product;

public interface IProductService {

	List<Product> findAll();

	Product findById(int id);

	void insert(Product product);

}
