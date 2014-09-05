package com.solon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solon.dao.spec.INetValueDao;
import com.solon.dto.NetValue;
import com.solon.service.spec.INetValueService;

@Service
public class NetValueServiceImpl implements INetValueService {

	@Autowired
	private INetValueDao netValueDao;

	@Override
	public List<NetValue> findByProductId(int productId) {
		return netValueDao.findByProductId(productId);
	}

}
