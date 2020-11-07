package com.tvdinh.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "[sensor]")
public class SensorEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="name", columnDefinition = "nvarchar(250)")
	private String name;
	@ManyToOne(fetch = FetchType.EAGER,optional = false)
	@JoinColumn(name = "sensor_type")
	private SensorTypeEntity sensorTypeEntity;
	@OneToMany(mappedBy = "sensorEntity",fetch = FetchType.LAZY)
	private List<SensorDataEnity> sensorDataList;
	
	@ManyToOne(optional = false,fetch = FetchType.EAGER)
	@JoinColumn(name = "device")
	private DeviceEntity deviceEntity;
	
	public DeviceEntity getDeviceEntity() {
		return deviceEntity;
	}
	public void setDeviceEntity(DeviceEntity deviceEntity) {
		this.deviceEntity = deviceEntity;
	}
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
	public SensorTypeEntity getSensorTypeEntity() {
		return sensorTypeEntity;
	}
	public void setSensorTypeEntity(SensorTypeEntity sensorTypeEntity) {
		this.sensorTypeEntity = sensorTypeEntity;
	}
	public List<SensorDataEnity> getSensorDataList() {
		return sensorDataList;
	}
	public void setSensorDataList(List<SensorDataEnity> sensorDataList) {
		this.sensorDataList = sensorDataList;
	}
	
}
