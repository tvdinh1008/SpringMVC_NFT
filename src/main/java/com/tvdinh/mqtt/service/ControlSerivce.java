package com.tvdinh.mqtt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class ControlSerivce implements IControlService{
    @Autowired
	private MqttPahoMessageHandler mqtt;
    
	@Override
	public void publishCommand(String topic, String data) {
		Message<String> message = MessageBuilder.withPayload(data).setHeader(MqttHeaders.TOPIC, topic).build();
		mqtt.handleMessage(message);
	}


}
