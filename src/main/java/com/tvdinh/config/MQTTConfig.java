package com.tvdinh.config;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledFuture;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
//import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
//import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
//import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.stereotype.Component;

/*
 * Có thể là do ta đang dùng spring 4 mà mqtt sư dụng spring 5
 */

@Configuration
@EnableAsync
@EnableScheduling
public class MQTTConfig {

	@Bean
	public TaskScheduler taskScheduler() {
		return new ConcurrentTaskScheduler();
	}

	// Of course , you can define the Executor too
	@Bean
	public Executor taskExecutor() {
		return new SimpleAsyncTaskExecutor();
	}

	@Bean
	public MqttPahoClientFactory mqttClientFactory() {
		DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
		factory.setServerURIs(new String[] {"tcp://mqtt.eclipse.org:1883"});
		factory.setUserName("username");
		factory.setPassword("password");
		factory.setCleanSession(true);
		factory.setKeepAliveInterval(10);
	
		return factory;
	}
	
	// subscriber
	@Bean
	public MessageProducer inbound() {
		MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter("clientId",
				mqttClientFactory(), "iot/nhom04","iot/nhom05");
		adapter.setCompletionTimeout(5000);
		adapter.setConverter(new DefaultPahoMessageConverter());
		adapter.setQos(2);
		adapter.setOutputChannel( outbount( ) );
		adapter.setAutoStartup(true);
		adapter.setTaskScheduler(taskScheduler()); //cái hay nhất là ở đây vì khi chạy mqtt subscriber cần tạo 1 task để chạy nhé !!!
		return adapter;
	}
	@Bean
	public PublishSubscribeChannel outbount() {
		PublishSubscribeChannel psc = new PublishSubscribeChannel( );
	    psc.subscribe( new MessageHandler( ) {
	        @Override
	        public void handleMessage( Message<?> message ) throws MessagingException {
	        	System.out.println("**************** Message : "+ message.getPayload() + " Headers:" +message.getHeaders());
	        }
	    } );
	    return psc;
	}
	
	//Publisher
	@Bean
	public MqttPahoMessageHandler mqqtMessageHandler() {
		MqttPahoMessageHandler messageHandler =new MqttPahoMessageHandler("clientId", mqttClientFactory());
		messageHandler.setAsync(true);
		messageHandler.setDefaultTopic("demo01");
		return messageHandler;
	    //return new MqttPahoMessageHandler( "Spring mvc" + "-pub", mqttClientFactory( ) );
	}
	
}
