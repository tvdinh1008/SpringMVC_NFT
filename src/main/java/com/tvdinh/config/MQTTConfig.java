package com.tvdinh.config;

import java.util.UUID;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;

/*
 * Có thể là do ta đang dùng spring 4 mà mqtt sư dụng spring 5
 */


@Configuration
public class MQTTConfig {
	/*
	@Bean
	public MqttPahoClientFactory mqttClientFactory() {
		DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
		MqttConnectOptions options = new MqttConnectOptions();
		options.setServerURIs(new String[] { "tcp://mqtt.eclipse.org:1883" });
		options.setUserName("username");
		options.setPassword("password".toCharArray());
		options.setAutomaticReconnect(true);
		options.setConnectionTimeout(30);
		options.setKeepAliveInterval(60);
		options.setCleanSession(true);
		factory.setConnectionOptions(options);
		return factory;
	}

	@Bean
	@ServiceActivator(inputChannel = "mqttPromiseOutboundChannel")
	public MqttPahoMessageHandler mqttPromiseOutbound() {
		String clientId2 = "uuid-" + UUID.randomUUID().toString();
		String topic = "/topic/client/tests";
		MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(clientId2, mqttClientFactory());
		messageHandler.setAsync(true);
		messageHandler.setDefaultTopic(topic);
		return messageHandler;
	}

	@Bean
	public MessageChannel mqttOutboundChannel() {
		return new DirectChannel();
	}

	@Bean
	public MessageChannel mqttPromiseOutboundChannel() {
		return new DirectChannel();
	}

	@Bean
	public IntegrationFlow mqttInFlow() {
		return IntegrationFlows.from(mqttInbound()).transform(p -> p + ", received from MQTT")
				.handle(new MessageHandler() {

					@Override
					public void handleMessage(Message<?> message) throws MessagingException {
						System.out.println("Got Message with Payload " + message.getPayload());
					}
				}).get();
	}

	@Bean
	public MessageProducerSupport mqttInbound() {
		MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter("siSampleConsumer",
				mqttClientFactory(), "Promise");
		adapter.setCompletionTimeout(5000);
		adapter.setConverter(new DefaultPahoMessageConverter());
		adapter.setQos(1);
		return adapter;
	}
	*/
}
