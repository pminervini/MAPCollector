package com.neuralnoise.service;

import com.neuralnoise.model.Message;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.message.GenericMessage;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class PublishServiceImpl implements PublishService {
	private static final Logger logger = Logger.getLogger(PublishServiceImpl.class);

	@Autowired
	private MessageChannel topicChannel;

	@Override
	public void send(Message message) {
		logger.info("Sending message to message channel: " + message);
		topicChannel.send(MessageBuilder.withPayload(message.toString()).build());
	}
}
