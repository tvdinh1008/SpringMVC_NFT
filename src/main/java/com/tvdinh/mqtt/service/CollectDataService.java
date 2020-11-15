package com.tvdinh.mqtt.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvdinh.dao.ISensorDao;
import com.tvdinh.dao.ISensorDataDao;
import com.tvdinh.entity.SensorDataEnity;
import com.tvdinh.entity.SensorEntity;
import com.tvdinh.mqtt.CollectData;

@Service
public class CollectDataService implements ICollectDataService{
	@Autowired
	private ISensorDao sensorDao;
	@Autowired
	private ISensorDataDao sensorDataDao;
	
	@Override
	@Transactional
	public void save(CollectData collectData) {
		List<SensorEntity> result=sensorDao.findByDeviceId(collectData.getId());
		for(SensorEntity item:result) {
			SensorDataEnity entity=new SensorDataEnity();
			entity.setSensorEntity(item);
			if(item.getName().equals("light")) {
				entity.setValue(collectData.getLight());
			}else if(item.getName().equals("temperature")) {
				entity.setValue(collectData.getTemperature());
			}else if(item.getName().equals("humidity")) {
				entity.setValue(collectData.getHumidity());
			}else if(item.getName().equals("ec")) {
				entity.setValue(collectData.getEc());
			}else if(item.getName().equals("ph")) {
				entity.setValue(collectData.getPh());
			}else if(item.getName().equals("watertemp")) {
				entity.setValue(collectData.getWater_temp());
			}
			sensorDataDao.save(entity);
		}
	}
}
