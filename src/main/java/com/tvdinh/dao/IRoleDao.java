package com.tvdinh.dao;

import java.util.List;

import com.tvdinh.entity.RoleEntity;

public interface IRoleDao extends GenericDao<Long, RoleEntity> {
	public List<RoleEntity> findAll();
}
