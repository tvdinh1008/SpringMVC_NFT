package com.tvdinh.mqtt.subscriber;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.stereotype.Component;

//@Component
public class MQTTSubscriber implements MqttCallback, MQTTSubscriberBase {

	//private static String topic = "mqtt/nhom04";
	private static int qos = 2;
	private static String serverUri = "tcp://mqtt.eclipse.org:1883";
	private MqttClient client = null;

	public MQTTSubscriber() {
		this.config();
	}

	protected void config() {
		try {
			client = new MqttClient(serverUri, MqttClient.generateClientId(), new MemoryPersistence());
			MqttConnectOptions connectOptions = new MqttConnectOptions();
			connectOptions.setUserName("username");
			connectOptions.setPassword("password".toCharArray());
			connectOptions.setAutomaticReconnect(true); //TH mất mạng ->nó sẽ tự động kết nối lại
			connectOptions.setCleanSession(true);
			connectOptions.setKeepAliveInterval(10);

			client.setCallback(this);
			client.connect(connectOptions);
			// client.subscribe(topic, qos);
		} catch (MqttException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//Phương thức để đăng ký topic
	@Override
	public void subscribeMessage(String topic) {
		try {
			this.client.subscribe(topic, qos);
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
	
	
	//Gọi callback khi có message gửi tới
	@Override
	public void connectionLost(Throwable cause) {
		System.out.println("Connection to MQTT broker lost!(subsciber)");
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		// Called when a message arrives from the server that matches any
		// subscription made by the client
		String time = new Timestamp(System.currentTimeMillis()).toString();
		System.out.println();
		System.out.println("***********************************************************************");
		System.out.println("Message Arrived at Time: " + time + " Qos:" + message.getQos() + " Topic: " + topic
				+ " ID: " + message.getId() + " data:" + new String(message.getPayload(), StandardCharsets.UTF_8));
		System.out.println("***********************************************************************");
		System.out.println();
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		System.out.println("deliveryComplete---------" + token.isComplete());
	}
}
