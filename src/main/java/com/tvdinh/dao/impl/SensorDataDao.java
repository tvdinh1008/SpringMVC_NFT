package com.tvdinh.dao.impl;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.tvdinh.dao.ISensorDataDao;
import com.tvdinh.entity.SensorDataEnity;

@Repository
public class SensorDataDao extends AbstractDao<Long , SensorDataEnity> implements ISensorDataDao{

	@Override
	@Transactional
	public SensorDataEnity findLastData(Long sensorId) {
		String sql="Select * from sensor_data Where sensor=:sensor order by id desc";
		SensorDataEnity entity=null;
		try {
		Query q=entityManager.createNativeQuery(sql.toString(),SensorDataEnity.class);
		q.setParameter("sensor", sensorId);
		entity=(SensorDataEnity) q.setMaxResults(1).getResultList().get(0);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return entity;
	}

	
}
