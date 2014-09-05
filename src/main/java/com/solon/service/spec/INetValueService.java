package com.solon.service.spec;

import java.util.List;

import com.solon.dto.NetValue;

public interface INetValueService {
	List<NetValue> findAll();

	List<NetValue> findByProductId(int productId);
}
