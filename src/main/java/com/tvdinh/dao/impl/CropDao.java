package com.tvdinh.dao.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.tvdinh.dao.ICropDao;
import com.tvdinh.entity.CropEntity;

@Repository
public class CropDao extends AbstractDao<Long, CropEntity> implements ICropDao {

	@Override
	public CropEntity findOneUserIdAndDeviceId(String username, Long deviceId) {
		CropEntity result = null;
		try {
			String sql = "Select t from " + getPersistenceClassName()
					+ " t JOIN FETCH t.userEntity u JOIN FETCH t.deviceEntity d"
					+ " where u.username=:username and d.id=:deviceId";
			Query q = entityManager.createQuery(sql);
			q.setParameter("username", username);
			q.setParameter("deviceId", deviceId);
			result = (CropEntity) q.getSingleResult();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return result;
	}

}
