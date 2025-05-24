package com.deliveryboy.app.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class kafkaConfig {
	
	@Bean
	public NewTopic topic() {
		 return TopicBuilder
				 .name(appContants.TOPIC1_NAME)
				 .build();
	}
	
	@KafkaListener(id="user2-messages", topics = appContants.TOPIC2_NAME, groupId = appContants.GROUP_ID, autoStartup = "false")
	public void readFromUser2(String value) {
		System.out.println(value);
	}
}
