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
@Table(name = "command")
public class CommandEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "actuator_name")
	private String actuatorName;
	@Column(name = "action")
	private String action;
	@Column(name = "param")
	private Float param;
	@Column(name = "source")
	private String source;
	@Column(name = "is_done")
	private int isDone;
	@ManyToOne(optional = false,fetch = FetchType.LAZY)
	@JoinColumn(name = "device")
	private DeviceEntity deviceEntity;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getActuatorName() {
		return actuatorName;
	}
	public void setActuatorName(String actuatorName) {
		this.actuatorName = actuatorName;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Float getParam() {
		return param;
	}
	public void setParam(Float param) {
		this.param = param;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public int getIsDone() {
		return isDone;
	}
	public void setIsDone(int isDone) {
		this.isDone = isDone;
	}
	public DeviceEntity getDeviceEntity() {
		return deviceEntity;
	}
	public void setDeviceEntity(DeviceEntity deviceEntity) {
		this.deviceEntity = deviceEntity;
	}
	
	
}
