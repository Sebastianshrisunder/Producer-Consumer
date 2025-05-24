package com.deliveryboy.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.deliveryboy.app.config.appContants;

@Service
public class kafkaService {
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	private Logger logger = LoggerFactory.getLogger(kafkaService.class);
	
	public boolean sendMessage(String message) {
		this.kafkaTemplate.send(appContants.TOPIC1_NAME, message);
		this.logger.info("Message produced by user1");
		return true;
	}
}
