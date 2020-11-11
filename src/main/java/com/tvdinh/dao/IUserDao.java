package com.tvdinh.dao;

import com.tvdinh.entity.UserEntity;

public interface IUserDao extends GenericDao<Long, UserEntity>{
	UserEntity findOneUsernameAndStatus(String username,int status);
}
