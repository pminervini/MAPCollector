package com.neuralnoise.service;

import com.neuralnoise.integration.util.CAnswer;
import com.neuralnoise.integration.util.CRequest;

public interface RequestService {
	
	public CAnswer process(CRequest request);
	
}
