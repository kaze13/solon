package com.solon.service.spec;

import java.util.List;

import com.solon.dto.NetValue;

public interface INetValueService {
	List<NetValue> findAll();

	List<NetValue> findByProductId(int productId);

	void insert(NetValue value);

	void update(NetValue value);

	void remove(int id);
	
	void removeByProduct(int pId);
	
	void insertByBatch(List<NetValue> values);
	
	void updateByBatch(List<NetValue> values);
	
	void deleteByBatch(List<NetValue> values);
	
}
