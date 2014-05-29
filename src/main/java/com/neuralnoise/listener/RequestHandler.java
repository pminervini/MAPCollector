package com.neuralnoise.listener;

import org.springframework.integration.annotation.Gateway;
import com.neuralnoise.model.Request;

public interface RequestHandler {

	@Gateway(requestChannel = "messages")
	void placeRequest(Request request);

}
