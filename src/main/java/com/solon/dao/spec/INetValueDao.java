package com.solon.dao.spec;

import java.util.List;

import com.solon.dto.NetValue;

public interface INetValueDao {

	List<NetValue> findByProductId(int productId);

	List<NetValue> findAll();
}
