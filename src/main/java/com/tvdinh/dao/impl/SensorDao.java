package com.tvdinh.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.tvdinh.dao.ISensorDao;
import com.tvdinh.entity.SensorEntity;

@Repository
public class SensorDao extends AbstractDao<Long, SensorEntity> implements ISensorDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<SensorEntity> findByDeviceId(Long id) {
		List<SensorEntity> result=new ArrayList<SensorEntity>();
		try {
			String sql="select t from "+getPersistenceClassName()+" t where device=:id and alive=1";
			Query q=entityManager.createQuery(sql);
			q.setParameter("id", id);
			result=q.getResultList();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	
	
	
}
