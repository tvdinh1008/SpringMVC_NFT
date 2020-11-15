package com.tvdinh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tvdinh.dao.IDeviceDao;
import com.tvdinh.dao.ISensorDataDao;
import com.tvdinh.entity.DeviceEntity;
import com.tvdinh.service.IDeviceService;

@Service
public class DeviceService implements IDeviceService {

	@Autowired
	private IDeviceDao deviceDao;
	@SuppressWarnings("unused")
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

	@Override
	public DeviceEntity save(DeviceEntity deviceEntity) {
		DeviceEntity result=null;
		if(deviceEntity!=null && deviceEntity.getId()!=null) {
			
			DeviceEntity old=deviceDao.findById(deviceEntity.getId());
			old.setAlive(deviceEntity.getAlive());
			
			result=deviceDao.update(old);
		}else if(deviceEntity!=null && deviceEntity.getId()==null) {
			result=deviceDao.save(deviceEntity);
		}
		return result;
	}

}
