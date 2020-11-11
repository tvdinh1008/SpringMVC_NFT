package com.tvdinh.service;

import com.tvdinh.entity.RoleEntity;

public interface IRoleService {
	
	public void findAll();
	RoleEntity findByCode(String code);
}
