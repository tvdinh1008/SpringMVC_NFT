package com.tvdinh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tvdinh.dao.IUserDao;
import com.tvdinh.entity.RoleEntity;
import com.tvdinh.entity.UserEntity;
import com.tvdinh.service.IRoleService;
import com.tvdinh.service.IUserService;

@Service
public class UserService implements IUserService {
	
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IRoleService roleService;
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Override
	public List<UserEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserEntity findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserEntity save() {
		UserEntity user=new UserEntity();
		user.setEmail("tvdinh@gmail.com");
		
		RoleEntity role=roleService.findByCode("USER");
		
		user.setUsername("chau");
		user.setPassword(passwordEncoder.encode("c"));
		
		
		user.setRole(role);
		//userDao.save(user);
		
		return null;
	}
}
