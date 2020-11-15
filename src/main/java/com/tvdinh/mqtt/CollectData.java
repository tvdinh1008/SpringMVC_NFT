package com.tvdinh.mqtt;

import java.sql.Timestamp;

public class CollectData {
	private Long id;
	private String device_token;
	private Timestamp timestamp;
	private Long packet_no;
	private Float temperature;
	private Float humidity;
	private Float ec;
	private Float light;
	public Float getLight() {
		return light;
	}
	public void setLight(Float light) {
		this.light = light;
	}
	private Float ph;
	private Float water_temp;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDevice_token() {
		return device_token;
	}
	public void setDevice_token(String device_token) {
		this.device_token = device_token;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public Long getPacket_no() {
		return packet_no;
	}
	public void setPacket_no(Long packet_no) {
		this.packet_no = packet_no;
	}
	public Float getTemperature() {
		return temperature;
	}
	public void setTemperature(Float temperature) {
		this.temperature = temperature;
	}
	public Float getHumidity() {
		return humidity;
	}
	public void setHumidity(Float humidity) {
		this.humidity = humidity;
	}
	public Float getEc() {
		return ec;
	}
	public void setEc(Float ec) {
		this.ec = ec;
	}
	public Float getPh() {
		return ph;
	}
	public void setPh(Float ph) {
		this.ph = ph;
	}
	public Float getWater_temp() {
		return water_temp;
	}
	public void setWater_temp(Float water_temp) {
		this.water_temp = water_temp;
	}
}
