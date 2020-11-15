package com.tvdinh.mqtt;

import com.tvdinh.entity.CropEntity;

public interface IAuthenticationService {
	CropEntity getCropIdAndUserId(String username, Long deviceId);
}
