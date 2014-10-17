package com.solon.service.spec;

import java.util.List;
import java.util.Map;


import com.solon.dto.Product;

public interface IProductService {

	List<Product> findAll();

	Product findById(int id);

	int insert(Product product);

	void remove(int id);

	void update(Product product);
	
	 
    int getProductsPagesCount(Map<String, String> condition);
	
    List<Product> getProductsByPaging(Map<String, String> condition, int page);
    
    int queryProductIdByName(String productName);
    

}
