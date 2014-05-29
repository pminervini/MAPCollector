package com.neuralnoise.listener;

import org.apache.log4j.Logger;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

import com.neuralnoise.model.Answer;
import com.neuralnoise.model.Request;

@Component("processor")
public class RequestProcessor {

	private static final Logger log = Logger.getLogger(RequestProcessor.class);

	@ServiceActivator(inputChannel = "requestProcessor", outputChannel = "answers")
	public Answer processRequest(Request request) {
		System.out.println("Processing request: " + request);
		return new Answer(request.getName(), request.getRequestType(), request.getBody());
	}

}
