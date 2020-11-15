package com.tvdinh.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tvdinh.authentication.JwtTokenProvider;
import com.tvdinh.entity.CropEntity;
import com.tvdinh.mqtt.AuthenticationClazz;
import com.tvdinh.mqtt.CollectData;
import com.tvdinh.mqtt.IAuthenticationService;
import com.tvdinh.mqtt.service.ICollectDataService;
import com.tvdinh.service.IDeviceService;

/*
 * Có thể là do ta đang dùng spring 4 mà mqtt sư dụng spring 5
 */

@Configuration
@EnableAsync
@EnableScheduling
public class MQTTConfig {
	private static final Logger logger = Logger.getLogger(MQTTConfig.class);
	@Autowired
	private JwtTokenProvider tokenProvider;
	@Autowired
	private IAuthenticationService authenticationService;
	@Autowired
	private ICollectDataService collectDataService;
	@Autowired
	private IDeviceService deviceService;
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
		factory.setServerURIs(new String[] { "tcp://mqtt.eclipse.org:1883" });
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
				mqttClientFactory(), "iot/nhom04", "iot/nhom05", "nct_authetication", "nct_keep_alive", "nct_collect","nct_collect_image");
		adapter.setCompletionTimeout(5000);
		adapter.setConverter(new DefaultPahoMessageConverter());
		adapter.setQos(2);
		adapter.setOutputChannel(outbount());
		adapter.setAutoStartup(true);
		adapter.setTaskScheduler(taskScheduler()); // cái hay nhất là ở đây vì khi chạy mqtt subscriber cần tạo 1 task
													// để chạy nhé !!!
		return adapter;
	}

	@Bean
	public PublishSubscribeChannel outbount() {
		PublishSubscribeChannel psc = new PublishSubscribeChannel();
		psc.subscribe(new MessageHandler() {
			@SuppressWarnings("resource")
			@Override
			public void handleMessage(Message<?> message) throws MessagingException {
				String received_topic = (String) message.getHeaders().get(MqttHeaders.TOPIC);
				// int qos=(int) message.getHeaders().get(MqttHeaders.QOS);
				//System.out.println("**************** Message : " + message.getPayload() + " Headers:" + message.getHeaders());
				if (received_topic.equals("nct_authetication")) {
				/*
				 * Sau khi authen thì device sẽ chuyển sang trạng thái sẵn sàng
				 */
					try {
						AuthenticationClazz auth = new ObjectMapper().readValue(message.getPayload().toString(),AuthenticationClazz.class);
						System.out.println("USER TOKEN: " + auth.getUser_token() + " ID DEVICE: " + auth.getId());	
						String topic_result = "nct_authentication_result_" + auth.getId();
						String data = "Authentication is successful";
						if (StringUtils.isNotBlank(auth.getUser_token()) && tokenProvider.validateJwtToken(auth.getUser_token())) {
							String username=tokenProvider.getUserNameFromJwtToken(auth.getUser_token());
							CropEntity crop=authenticationService.getCropIdAndUserId(username,auth.getId());
							if(crop!=null) {
								System.out.println(" "+crop.getDevice().getId()+" " +crop.getId()+" "+ crop.getUser().getId());
								//Thay đổi trạng thái của device;
								String jwt=tokenProvider.generateToken(crop.getUser().getId(), crop.getId(), crop.getUser().getId());
								System.out.println("Device token: "+jwt);
								crop.getDevice().setAlive(1);
								if(deviceService.save(crop.getDevice())!=null) {
									// nếu xác thực thành công
									Message<String> messageauth = MessageBuilder.withPayload(data).setHeader(MqttHeaders.TOPIC, topic_result).build();
									mqqtMessageHandler().handleMessage(messageauth);		
									return;
								}	
							}
						}
						data="Authentication is false";
						Message<String> messageauth = MessageBuilder.withPayload(data).setHeader(MqttHeaders.TOPIC, topic_result).build();
						mqqtMessageHandler().handleMessage(messageauth);					
					} catch (Exception e) {
						logger.error(e.getMessage());
					}
				}
				
				if (received_topic.equals("nct_keep_alive")) {
					//Sau 1 phút ESP sẽ gửi keep_alive để kiểm tra
				}
				if (received_topic.equals("nct_collect")) {
					// xác thực thông tin: deviceId, sensor data, DEVICE_TOKEN
					
					//Hoàn toàn có thể lấy timestamp ở message.getHeader nhé (qua get(Key));
					try {
						CollectData collectData=new ObjectMapper().readValue(message.getPayload().toString(),CollectData.class);
						if(StringUtils.isNotBlank(collectData.getDevice_token()) && tokenProvider.validateJwtToken(collectData.getDevice_token())) {
							//insert vào trong database.
							List<Long> listId=tokenProvider.parseToken(collectData.getDevice_token());
							//insert data
							if(listId!=null) {
								if(collectData.getId()==listId.get(2)) {
									collectDataService.save(collectData);
								}
							}
						}
					} catch (IOException e) {
						logger.error(e.getMessage());
					}
				}
				//Nhận ảnh
				if(received_topic.equals("nct_collect_image")) {
					try {
						byte data1[]=Base64.getDecoder().decode((String)message.getPayload());
						//byte data[] = ((String)message.getPayload()).getBytes();
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
						String timeStamp = dateFormat.format(new Date());
						String imageFileName = "picture_" + timeStamp + ".jpg";
					
						File photo=new File("E:\\JAVA_WEB\\tmp\\"+imageFileName);
						FileOutputStream fileOuputStream = new FileOutputStream(photo);
						fileOuputStream.write(data1);
						fileOuputStream.close();
					} catch (Exception e) {
						logger.error(e.getMessage());
					}
	                
				}
			}
		});
		return psc;
	}

	// Publisher
	@Bean
	public MqttPahoMessageHandler mqqtMessageHandler() {
		MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler("clientId", mqttClientFactory());
		messageHandler.setAsync(true);
		messageHandler.setDefaultTopic("demo01");
		return messageHandler;
	}

}
