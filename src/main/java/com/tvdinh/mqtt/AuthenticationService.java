package com.tvdinh.mqtt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tvdinh.dao.ICropDao;
import com.tvdinh.entity.CropEntity;

@Component
public class AuthenticationService implements IAuthenticationService{
	@Autowired
	private ICropDao cropDao;

	@Override
	public CropEntity getCropIdAndUserId(String username, Long deviceId) {
		CropEntity crop=cropDao.findOneUserIdAndDeviceId(username, deviceId);
		
		
		return crop;
	}

}
