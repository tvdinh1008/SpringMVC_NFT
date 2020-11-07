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
@Table (name = "[actuator_type]")
public class ActuatorTypeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="name", columnDefinition = "nvarchar(250)")
	private String name;
	
	@OneToMany(mappedBy = "actuatorTypeEntity",fetch = FetchType.LAZY)
	private List<ActuatorEntity> actuatorList;

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

	public List<ActuatorEntity> getActuatorList() {
		return actuatorList;
	}

	public void setActuatorList(List<ActuatorEntity> actuatorList) {
		this.actuatorList = actuatorList;
	}
	
}
