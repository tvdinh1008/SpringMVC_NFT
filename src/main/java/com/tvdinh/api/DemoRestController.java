package com.tvdinh.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DemoRestController {
	
	//@Autowired
	//private MQTTPublisherBase publisher;
	
	//private static String topic = "mqtt/nhom04";
	
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/api/publisher",method = RequestMethod.GET)
	public String index() {
		String message="Hello ace nhé!";
	//	publisher.publishMessage(topic, message);
		return "message send to broker";
	}
}
