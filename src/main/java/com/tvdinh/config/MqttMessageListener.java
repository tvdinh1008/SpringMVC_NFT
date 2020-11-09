package com.tvdinh.config;

import org.springframework.stereotype.Component;


@Component
public class MqttMessageListener implements Runnable {
	
	//@Autowired
	//MQTTSubscriberBase subscriber;

	@Override
	public void run() {
		/*
		 * Nếu ko while TH mà nó mất kết nối với broker ->nó đã thoát run này rồi -> Ko
		 * nhận được dữ liệu nếu thoát khỏi run (do subscribeMessage chạy bất đồng bộ nó
		 * vẫn nhận dữ liệu tức chỉ cần gọi 1 lần- nếu mất kết nối thì nó thoát khỏi cái
		 * này)
		 */
		/*
		while (true) {

			subscriber.subscribeMessage("mqtt/nhom04");
			subscriber.subscribeMessage("mqtt/nhom05");

		}
		*/
	}

}