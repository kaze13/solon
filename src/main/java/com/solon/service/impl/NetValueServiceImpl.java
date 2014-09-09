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

	@Override
	public List<NetValue> findAll() {
		return netValueDao.findAll();

	}
	
	@Override
	public void insert(NetValue value){
		netValueDao.insert(value);
	}
	
	@Override
	public void update(NetValue value){
		netValueDao.update(value);
	}
	
	@Override
	public void remove(int id){
		netValueDao.remove(id);
	}

}
