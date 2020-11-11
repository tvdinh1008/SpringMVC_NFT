package com.tvdinh.dao.impl;

import com.tvdinh.dao.IRoleDao;
import com.tvdinh.entity.RoleEntity;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository
public class RoleDao extends AbstractDao<Long, RoleEntity> implements IRoleDao {

	@Override
	public RoleEntity findByCode(String code) {
		RoleEntity result = null;
		try {
			StringBuilder sql = new StringBuilder("");
			sql.append("Select * from role Where code=:code");
			Query q = entityManager.createNativeQuery(sql.toString(), RoleEntity.class);
			q.setParameter("code", code);
			result = (RoleEntity) q.getSingleResult();

		} catch (Exception e) {

		}
		return result;
	}

}
