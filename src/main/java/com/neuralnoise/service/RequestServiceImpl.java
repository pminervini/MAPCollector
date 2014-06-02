package com.neuralnoise.service;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.neuralnoise.collect.AdapterFactory;
import com.neuralnoise.collect.IAdapter;
import com.neuralnoise.collect.ICollectible;
import com.neuralnoise.collect.event.Event;
import com.neuralnoise.integration.util.CAnswer;
import com.neuralnoise.integration.util.CEvent;
import com.neuralnoise.integration.util.CRequest;

@Service
public class RequestServiceImpl implements RequestService {
	
	private static final Logger log = Logger.getLogger(RequestServiceImpl.class);

	@Override
	public CAnswer process(CRequest request) throws Exception {
		
		AdapterFactory af = new AdapterFactory();
		IAdapter adapter = af.adapterName(request.getAdapterName()).resource(request.getResource()).build();
		
		Collection<ICollectible> collectibles = adapter.collect();
		
		CAnswer answer = new CAnswer();
		answer.setRequest(request);
		
		List<CEvent> events = Lists.newLinkedList();
		for (ICollectible collectible : collectibles) {
			Event event = (Event) collectible;

			CEvent cEvent = new CEvent();
			cEvent.setName(event.getName());
			cEvent.setContent(event.getDescription());
			cEvent.setStartDate(event.getStartDate());
			cEvent.setEndDate(event.getEndDate());
			cEvent.setLocation(event.getLocation());
		
			log.info("Sending cEvent: " + cEvent);
			
			events.add(cEvent);
		}
		
		answer.setEvents(events);
		return answer;
	}

}
