package com.tvdinh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import com.tvdinh.authentication.JwtTokenProvider;
import com.tvdinh.dao.ICropDao;
import com.tvdinh.entity.CropEntity;
import com.tvdinh.mqtt.service.IControlService;
import com.tvdinh.service.ICropService;

public class CropService implements ICropService {
    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
	private IControlService controlService;
    
	@Autowired
	private ICropDao cropDao;
	@Override
	public CropEntity save(CropEntity crop) {
		CropEntity result=null;
		//Thêm mới
		if(crop!=null && crop.getId()==null) {
			result=cropDao.save(crop);
			if(result!=null) {
				// publish mqtt keep alive 
				//crop ID, user ID and device ID 
				String jwt=tokenProvider.generateToken(result.getUser().getId(), result.getId(), result.getDevice().getId());
				String topic="create_crop_command";
				// nếu xác thực thành công
				controlService.publishCommand(topic, jwt);
			}
		}
		//Cập nhật
		else if(crop!=null && crop.getId()!=null) {
			
		}
		
		
		return result;
	}

}
