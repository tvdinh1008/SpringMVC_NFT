package com.tvdinh.entity;

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
@Table (name = "[actuator]")
public class ActuatorEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="name", columnDefinition = "nvarchar(250)")
	private String name;
	@Column(name = "stastus")
	private String status;
	
	@ManyToOne(optional = false,fetch = FetchType.LAZY)
	@JoinColumn(name = "actuator_type")
	private ActuatorTypeEntity actuatorTypeEntity;
	
	@ManyToOne(optional = false,fetch = FetchType.LAZY)
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ActuatorTypeEntity getActuatorTypeEntity() {
		return actuatorTypeEntity;
	}

	public void setActuatorTypeEntity(ActuatorTypeEntity actuatorTypeEntity) {
		this.actuatorTypeEntity = actuatorTypeEntity;
	}

	
}
