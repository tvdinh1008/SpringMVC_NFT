package com.tvdinh.service;

import java.util.List;

import com.tvdinh.entity.SensorEntity;

public interface ISensorService {
	List<SensorEntity> getAllWithDeviceId(Long id);
}
