package com.tvdinh.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvdinh.dao.IDeviceDao;
import com.tvdinh.dao.ISensorDataDao;
import com.tvdinh.entity.DeviceEntity;
import com.tvdinh.entity.SensorDataEnity;
import com.tvdinh.entity.SensorEntity;
import com.tvdinh.service.IDeviceService;

@Service
public class DeviceService implements IDeviceService {

	@Autowired
	private IDeviceDao deviceDao;
	@Autowired
	private ISensorDataDao sensorDataDao;

	@Override
	public DeviceEntity getAllDataSensor(Long id) {
		DeviceEntity d = deviceDao.getAllDataSensorOneDevice(1l);
		/*for (SensorEntity data : d.getSensorList()) {
			data.setSensorDataList(new ArrayList<SensorDataEnity>());
			SensorDataEnity i = sensorDataDao.findLastData(data.getId());
			if (i != null) {
				data.getSensorDataList().add(i);

			}
		}*/
		return d;
	}

}
