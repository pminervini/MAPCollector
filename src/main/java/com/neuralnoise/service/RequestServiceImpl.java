package com.neuralnoise.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.neuralnoise.integration.util.CAnswer;
import com.neuralnoise.integration.util.CRequest;

@Service
public class RequestServiceImpl implements RequestService {
	
	private static final Logger log = Logger.getLogger(RequestServiceImpl.class);

	@Override
	public CAnswer process(CRequest request) {
		
		
		return null;
	}

}
