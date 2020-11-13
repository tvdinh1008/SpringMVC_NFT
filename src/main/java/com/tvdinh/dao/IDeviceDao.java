package com.tvdinh.dao;

import com.tvdinh.entity.DeviceEntity;

public interface IDeviceDao extends GenericDao<Long, DeviceEntity> {
	DeviceEntity getAllDataSensorOneDevice(Long id);
}
