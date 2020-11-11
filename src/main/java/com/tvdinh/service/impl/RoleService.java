package com.tvdinh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvdinh.dao.IRoleDao;
import com.tvdinh.entity.RoleEntity;
import com.tvdinh.repository.RoleRepository;
import com.tvdinh.service.IRoleService;

@Service
public class RoleService implements IRoleService {
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private IRoleDao roleDao;
	
	@Override
	public void findAll() {
		List<RoleEntity>list=roleRepository.findAll();
		list=roleDao.findAll();
		int k=0;
	}

	@Override
	public RoleEntity findByCode(String code) {
		RoleEntity role=roleDao.findByCode(code);
		return role;
	}

}
