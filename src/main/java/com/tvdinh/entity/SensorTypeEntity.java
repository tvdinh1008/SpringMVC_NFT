package com.tvdinh.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "[sensor_type]")
public class SensorTypeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="name", columnDefinition = "nvarchar(250)")
	private String name;
	
	@OneToMany(mappedBy = "sensorTypeEntity",fetch = FetchType.LAZY)
	private List<SensorEntity> sensorList;

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

	public List<SensorEntity> getSensorList() {
		return sensorList;
	}

	public void setSensorList(List<SensorEntity> sensorList) {
		this.sensorList = sensorList;
	}
}