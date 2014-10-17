package com.solon.dao.spec;

import java.util.List;
import java.util.Map;

import com.solon.dto.Product;

public interface IProductDao {

	public List<Product> findAll();

	Product findById(int id);

	int insert(Product product);

	void update(Product product);

	void remove(int id);
	
	
	public int getProductsPagesCount(Map<String, String> condition);

	
	public List<Product> getProductsByPaging(Map<String, String> condition, int page);
	
	public int queryProductIdByName(String productName);

}
