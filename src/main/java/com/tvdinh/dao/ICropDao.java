package com.tvdinh.dao;

import com.tvdinh.entity.CropEntity;

public interface ICropDao extends GenericDao<Long, CropEntity> {
	CropEntity findOneUserIdAndDeviceId(String username, Long deviceId);
}
