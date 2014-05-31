package com.neuralnoise.integration;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

import com.neuralnoise.integration.util.CAnswer;
import com.neuralnoise.integration.util.CRequest;

@Component
public class RequestHandler {

	@ServiceActivator
	public CAnswer handle(CRequest request) {
		System.out.println("request: " + request);
		
		CAnswer answer = new CAnswer();
		answer.setRequest(request);
		
		return answer;
	}

}
