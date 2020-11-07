package com.tvdinh.model;

import java.util.List;

public class PlantModel {
	private Long id;
	private String name;
	private Integer early_day;
	private Integer mid_day;
	private Integer late_day;
	private Float min_ec;
	private Float max_ec;
	private Float min_ph;
	private Float max_ph;
	private String type;
	private Integer verified;
	private List<CropModel> cropList;
	
	
	public Float getMin_ph() {
		return min_ph;
	}
	public void setMin_ph(Float min_ph) {
		this.min_ph = min_ph;
	}
	public Float getMax_ph() {
		return max_ph;
	}
	public void setMax_ph(Float max_ph) {
		this.max_ph = max_ph;
	}
	public List<CropModel> getCropList() {
		return cropList;
	}
	public void setCropList(List<CropModel> cropList) {
		this.cropList = cropList;
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
	public Integer getEarly_day() {
		return early_day;
	}
	public void setEarly_day(Integer early_day) {
		this.early_day = early_day;
	}
	public Integer getMid_day() {
		return mid_day;
	}
	public void setMid_day(Integer mid_day) {
		this.mid_day = mid_day;
	}
	public Integer getLate_day() {
		return late_day;
	}
	public void setLate_day(Integer late_day) {
		this.late_day = late_day;
	}
	public Float getMin_ec() {
		return min_ec;
	}
	public void setMin_ec(Float min_ec) {
		this.min_ec = min_ec;
	}
	public Float getMax_ec() {
		return max_ec;
	}
	public void setMax_ec(Float max_ec) {
		this.max_ec = max_ec;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getVerified() {
		return verified;
	}
	public void setVerified(Integer verified) {
		this.verified = verified;
	}
}
