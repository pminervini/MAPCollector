package com.neuralnoise.service;

import org.springframework.stereotype.Service;

@Service
public class RetrievePayloadServiceImpl implements RetrievePayloadService {
	
	@Override
	public String getPayload(String id) {
		return "Payload for " + id;
	}
	
}
