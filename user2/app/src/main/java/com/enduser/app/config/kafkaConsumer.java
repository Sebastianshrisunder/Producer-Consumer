package com.enduser.app.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;

import com.enduser.app.AppConstants;

@Configuration
public class kafkaConsumer {
	
	@Bean
	public NewTopic topic() {
		return TopicBuilder
				.name(AppConstants.TOPIC2_NAME)
				.build();
	}
	
	@KafkaListener(id="user1-messages",topics= AppConstants.TOPIC1_NAME,
			groupId = AppConstants.GROUP_ID, autoStartup = "false")
	public void updatedLocation(String value) {
		
		System.out.println(value);
		
	}
}
