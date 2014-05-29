package com.neuralnoise.listener;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Router;

import com.neuralnoise.model.Request;

@MessageEndpoint
public class RequestRouter {

	@Router(inputChannel = "messages")
	public String router(Request request) {
		System.out.println("Routing request: " + request);
		return "requests";
	}

}
