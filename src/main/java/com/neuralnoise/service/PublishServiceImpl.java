package com.neuralnoise.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.neuralnoise.model.Message;

@Service
public class PublishServiceImpl implements PublishService {
	
	private static final Logger log = Logger.getLogger(PublishServiceImpl.class);

	@Autowired
	private MessageChannel mapChannel;

	@Override
	public void send(Message message) {
		log.info("Sending message to message channel: " + message);
		mapChannel.send(MessageBuilder.withPayload(message.toString()).build());
	}
}
