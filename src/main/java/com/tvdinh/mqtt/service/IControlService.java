package com.tvdinh.mqtt.service;

public interface IControlService {
	void publishCommand(String topic, String data);
}
