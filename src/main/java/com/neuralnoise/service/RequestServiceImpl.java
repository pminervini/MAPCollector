package com.neuralnoise.service;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

//import com.neuralnoise.integration.RequestHandler;
import com.neuralnoise.model.Request;

@Service
public class RequestServiceImpl implements RequestService, ApplicationContextAware {
	
	private static final Logger log = Logger.getLogger(RequestServiceImpl.class);

    private ApplicationContext applicationContext;
    
	@Override
	public void send(Request request) {
		//System.out.println("Handling request " + request + " ..");
		//RequestHandler rh = (RequestHandler) getApplicationContext().getBean("collector");
		//rh.placeRequest(request);
	}
	

    private ApplicationContext getApplicationContext() {
        return applicationContext;
    }

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	
}
