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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
	public DeviceEntity getDevice() {
		return device;
	}
	public void setDevice(DeviceEntity device) {
		this.device = device;
	}
	public PlantEntity getPlant() {
		return plant;
	}
	public void setPlant(PlantEntity plant) {
		this.plant = plant;
	}
	
}
