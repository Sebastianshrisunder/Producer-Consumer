package com.enduser.app.config;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user2")
public class ConsumerController {
	
	private KafkaListenerEndpointRegistry registry; 
	private consumerService service;
	
	
	@Autowired
	public ConsumerController(KafkaListenerEndpointRegistry registry,consumerService service) {
		this.registry = registry;
		this.service = service;
	}
	
	
	private boolean isActive = false;
	
	@PostMapping("/read")
	public ResponseEntity<String> readMessage() {
		if(Boolean.FALSE.equals( isActive)) {
			registry.getListenerContainer("user1-messages").start();
			isActive = true;
			return new  ResponseEntity<String>("Messages consuming started from user1", HttpStatus.OK);
		}
		else {
			registry.getListenerContainer("user1-messages").stop();
			isActive = false;
			return new  ResponseEntity<String>("Messages consuming stopped from user1", HttpStatus.OK);
		}
	}
	
	@PostMapping("/send")
	public ResponseEntity<?> stopReading(@RequestBody String message) {
		service.sendMessages(message);
		return new ResponseEntity<>(Map.of("message","message sent to user 1"), HttpStatus.OK);
	}
	
}
