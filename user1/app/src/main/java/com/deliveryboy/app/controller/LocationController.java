package com.deliveryboy.app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deliveryboy.app.service.kafkaService;

@RestController
@RequestMapping("/user1")
public class LocationController {
	
	private kafkaService service;
	private KafkaListenerEndpointRegistry registry;
	
	@Autowired
	public LocationController(kafkaService service, KafkaListenerEndpointRegistry registry ) {
		this.service = service;
		this.registry = registry;
	}
	
	private boolean isActive = false;
	
	@PostMapping("/send")
	public ResponseEntity<?> updateLocation(@RequestBody String message){
		service.sendMessage(message);
		return new ResponseEntity<>(Map.of("message", "message sent to user 2"), HttpStatus.OK);
	}
	
	@PostMapping("/read")
	public ResponseEntity<String> readMessages(){
		if(Boolean.FALSE.equals(isActive)) {
			registry.getListenerContainer("user2-messages").start();
			isActive = true;
			return new ResponseEntity<String>("Message consuming started from user2", HttpStatus.OK);
		}
		else {
			registry.getListenerContainer("user2-messages").stop();
			isActive = false;
			return new ResponseEntity<String>("Message consuming stopped from user2", HttpStatus.OK);
		}
	}
}
