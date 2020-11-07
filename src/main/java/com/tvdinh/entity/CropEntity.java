package com.tvdinh.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "[crop]")
public class CropEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name="start_time")
	private Date start_time;
	@Column(name = "end_time")
	private Date end_time;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "user_id")
	private UserEntity userEntity;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "device_id")
	private DeviceEntity deviceEntity;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "plant_id")
	private PlantEntity plantEntity;
	
	
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
		return userEntity;
	}
	public void setUser(UserEntity user) {
		this.userEntity = user;
	}
	public DeviceEntity getDevice() {
		return deviceEntity;
	}
	public void setDevice(DeviceEntity device) {
		this.deviceEntity = device;
	}
	public PlantEntity getPlant() {
		return plantEntity;
	}
	public void setPlant(PlantEntity plant) {
		this.plantEntity = plant;
	}
}
