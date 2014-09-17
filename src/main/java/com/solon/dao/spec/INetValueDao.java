package com.solon.dao.spec;

import java.util.List;

import com.solon.dto.NetValue;

public interface INetValueDao {

	List<NetValue> findByProductId(int productId);

	List<NetValue> findAll();

	void insert(NetValue value);

	void update(NetValue value);

	void remove(int id);
	
	void removeByProduct(int pId);
}
