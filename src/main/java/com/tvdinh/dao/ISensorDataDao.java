package com.tvdinh.dao;

import com.tvdinh.entity.SensorDataEnity;

public interface ISensorDataDao extends GenericDao<Long, SensorDataEnity>{
	SensorDataEnity findLastData(Long sensorId);
}
