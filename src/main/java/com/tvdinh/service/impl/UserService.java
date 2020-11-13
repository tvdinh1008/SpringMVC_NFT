package com.tvdinh.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tvdinh.dao.IUserDao;
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
		return userDao.findAll();
	}

	@Override
	public UserEntity findById(Long id) {
		return userDao.findById(id);
	}
	
	@Override
	public UserEntity save(UserEntity userEntity) {
		UserEntity user=null;
		if(userEntity.getId()!=null) {
			/*
			 * Khi update cần giữ lại những thông tin ko tác động tới
			 */
			UserEntity oldEntity=userDao.findById(userEntity.getId());
			if(userEntity.getRole()==null) {
				userEntity.setRole(oldEntity.getRole());
			}
			if(userEntity.getStatus()==0) {
				userEntity.setStatus(oldEntity.getStatus());
			}
			user=userDao.update(userEntity);
		}else {
			if(userEntity.getStatus()==0) {
				userEntity.setStatus(1);
			}
			userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
			if(userEntity.getRole()==null) {
				userEntity.setRole(roleService.findByCode("USER"));
			}
			user=userDao.save(userEntity);
		}
		return user;
	}
	
	/*
	 * Trả về danh sách các entity thỏa mãn và count của nó
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object[] findByProperties(Map<String, Object> property, Integer offset, Integer limit, String sortExpression,
			String sortDirection) {
		List<UserEntity> result=new ArrayList<UserEntity>();
		String JOIN_FETCH="";
		Object[] objects=userDao.findByProperties(property, offset, limit, sortExpression, sortDirection, JOIN_FETCH);
		
		for(UserEntity item:(List<UserEntity>)objects[1]) {
			result.add(item);
		}
		objects[1]=result;
		return objects;
	}

	@Override
	public Boolean deleteById(Long[] ids) {
		return (ids.length==userDao.delete(ids));
	}

	@Override
	public UserEntity findByUsernameAndPassword(UserEntity userEntity) {
		//UserEntity entity=userDao.findOneUsernameAndStatus(userEntity.getUsername(), (userEntity.getStatus()==0)?1:userEntity.getStatus());
		UserEntity entity=userDao.findOneUsername(userEntity.getUsername());
		if(entity!=null) {
			if(passwordEncoder.matches(userEntity.getPassword(), entity.getPassword())) {
				return entity;
			}
		}
		return null;
	}
}
