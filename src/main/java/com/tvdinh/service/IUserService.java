package com.tvdinh.service;

import java.util.List;
import java.util.Map;

import com.tvdinh.entity.UserEntity;

public interface IUserService {
	public List<UserEntity> findAll();
	public UserEntity findById(Long id);
	UserEntity save(UserEntity userEntity);
	Object[] findByProperties(Map<String, Object> property, Integer offset, Integer limit, String sortExpression, String sortDirection);
	Boolean deleteById(Long[] ids);
	public UserEntity findByUsernameAndPassword(UserEntity userEntity);
}

