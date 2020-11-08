package com.tvdinh.mqtt.publisher;

import java.nio.charset.StandardCharsets;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.stereotype.Component;

@Component
public class MQTTPublisher implements MqttCallback, MQTTPublisherBase {
	
	//private static String topic = "mqtt/nhom04";
    private static int qos=2;
    private static String serverUri="tcp://mqtt.eclipse.org:1883";
    private MqttClient client = null;
    
    //sử dụng singleton
    private MQTTPublisher() {
		this.config();
	}
	protected void config() {
		try {
			client = new MqttClient(serverUri, MqttClient.generateClientId(), new MemoryPersistence());
			MqttConnectOptions connectOptions = new MqttConnectOptions();
			connectOptions.setUserName("username");
			connectOptions.setPassword("password".toCharArray());
			connectOptions.setAutomaticReconnect(true);
			connectOptions.setCleanSession(true);
			connectOptions.setKeepAliveInterval(10);

			client.setCallback(this);
			client.connect(connectOptions);

			
		} catch (MqttException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static MQTTPublisher getInstance() {
		return new MQTTPublisher();
	}
	
	
	//service publisher
	@Override
	public void publishMessage(String topic, String message) {
		try {
			client.publish(topic, message.getBytes(StandardCharsets.UTF_8), qos, false);
		} catch (MqttException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void disconnect() {
		try {
			this.client.disconnect();
		} catch (MqttException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	
	
	@Override
	public void connectionLost(Throwable cause) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		// TODO Auto-generated method stub
		
	}

}
