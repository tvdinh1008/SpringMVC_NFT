package com.tvdinh.config;

//import org.springframework.beans.factory.SmartInitializingSingleton;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.core.task.SimpleAsyncTaskExecutor;
//import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableAsync
@EnableScheduling
public class AppConfig {
	/*
	@Autowired
	MqttMessageListener mqttMessageListener;
	
	@Bean
	public TaskExecutor taskExecutor() {
		return new SimpleAsyncTaskExecutor();
	}
	@Bean
	public SmartInitializingSingleton importProcessor() {
		return () -> {
			taskExecutor().execute(mqttMessageListener);
		};
	}
	*/

}
