package com.tvdinh.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tvdinh.mqtt.publisher.MQTTPublisherBase;

@RestController
public class DemoRestController {
	
	@Autowired
	private MQTTPublisherBase publisher;
	
	private static String topic = "mqtt/nhom04";
	
	
	@RequestMapping(value = "/api/publisher",method = RequestMethod.GET)
	public String index() {
		String message="Hello ace nhé!";
		publisher.publishMessage(topic, message);
		return "message send to broker";
	}
}
