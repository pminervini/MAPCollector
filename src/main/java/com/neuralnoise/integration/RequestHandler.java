package com.neuralnoise.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

import com.neuralnoise.integration.util.CAnswer;
import com.neuralnoise.integration.util.CRequest;
import com.neuralnoise.service.RequestService;

@Component
public class RequestHandler {

	private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);
	
	@Autowired
	private RequestService requestService;
	
	@ServiceActivator
	public CAnswer handle(CRequest request) throws Exception {
		log.info("Received request: " + request);
		CAnswer answer = requestService.process(request);
		log.info("Returning answers (" + (answer.getEvents() != null ? answer.getEvents().size() : 0) + " events) ..");
		return answer;
	}

}
