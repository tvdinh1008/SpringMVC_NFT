package com.tvdinh.dao.impl;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.tvdinh.dao.IDeviceDao;
import com.tvdinh.entity.DeviceEntity;

@Repository
public class DeviceDao extends AbstractDao<Long, DeviceEntity> implements IDeviceDao {

	@Override
	@Transactional
	public DeviceEntity getAllDataSensorOneDevice(Long id) {
		DeviceEntity result=null;
		String sql="Select t from " 
		+ getPersistenceClassName() +" t JOIN FETCH t.sensorList where t.id=:id ";
		
		Query q =  entityManager.createQuery(sql);
		q.setParameter("id", id);
		result=(DeviceEntity) q.getSingleResult();
		
		return result;
	}

}
