package com.neuralnoise.service;

import org.springframework.stereotype.Service;

@Service
public class RetrievePayloadServiceImpl implements RetrievePayloadService {
	
	@Override
	public String getPayload(String id) {
		// Go back to the SOR and retrieve the payload for the specified id ...
		return "Payload for " + id;
	}
	
}
