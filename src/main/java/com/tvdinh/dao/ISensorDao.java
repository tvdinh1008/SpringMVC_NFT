package com.tvdinh.dao;

import java.util.List;

import com.tvdinh.entity.SensorEntity;

public interface ISensorDao extends GenericDao<Long, SensorEntity>{
	List<SensorEntity> findByDeviceId(Long id);

}
