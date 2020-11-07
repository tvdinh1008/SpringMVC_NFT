package com.tvdinh.model;

import java.util.Date;

import com.tvdinh.entity.DeviceEntity;
import com.tvdinh.entity.PlantEntity;
import com.tvdinh.entity.UserEntity;

public class CropModel {
	private Long id;
	private String name;
	private Date start_time;
	private Date end_time;
	private UserEntity user;
	private DeviceEntity device;
	private PlantEntity plant;
}
