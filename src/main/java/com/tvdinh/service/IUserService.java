package com.tvdinh.service;

import java.util.List;

import com.tvdinh.entity.UserEntity;

public interface IUserService {
	public List<UserEntity> findAll();
	public UserEntity findById(Long id);
}

