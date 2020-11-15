package com.tvdinh.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tvdinh.entity.SensorEntity;
import com.tvdinh.service.ISensorService;

@Service
public class SensorService implements ISensorService{
	
	
	@Override
	public List<SensorEntity> getAllWithDeviceId(Long id) {
		
		return null;
	}

}
