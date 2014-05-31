package com.neuralnoise.integration;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

@Component
public class RequestHandler {

	@ServiceActivator(inputChannel="requests", outputChannel="answers")
	public Request handle(Request request) {
		System.out.println("request: " + request);
		return request;
	}

	
}
