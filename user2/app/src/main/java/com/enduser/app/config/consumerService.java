package com.enduser.app.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.enduser.app.AppConstants;

@Service
public class consumerService {
	
	@Autowired
	private KafkaTemplate<String,String> kafkaTemplate;
	
	private Logger logger = LoggerFactory.getLogger(consumerService.class);
	
	public boolean sendMessages(String message) {
		this.kafkaTemplate.send(AppConstants.TOPIC2_NAME, message);
		this.logger.info("Message produced by user2");
		return true;
	}
	
}
