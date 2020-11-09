package com.tvdinh.controller.web;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tvdinh.service.IRoleService;

@Controller(value = "homeControllerOfWeb")
public class HomeController {

	@Autowired
	private IRoleService roleService;
	@Autowired
	MqttPahoMessageHandler mqtt;

	@RequestMapping(value = "/trang-chu", method = RequestMethod.POST)
	public ModelAndView homePage() {
		ModelAndView mav = new ModelAndView("web/home");
		roleService.findAll();
		return mav;
	}

	@RequestMapping(value = "/trang-chu", method = RequestMethod.GET)
	public ModelAndView getHomePage() throws MqttPersistenceException, MqttException {
		String data = "Hế lo các bạn nhé !!!";
		Message<String> message = MessageBuilder.withPayload(data).setHeader(MqttHeaders.TOPIC, "demotopic").build();
		mqtt.handleMessage(message);

		return new ModelAndView("web/home");
	}
}
