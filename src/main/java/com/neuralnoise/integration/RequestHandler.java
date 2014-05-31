package com.neuralnoise.integration;

import java.util.List;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.neuralnoise.integration.geo.Location;
import com.neuralnoise.integration.geo.Point;
import com.neuralnoise.integration.util.CAnswer;
import com.neuralnoise.integration.util.CEvent;
import com.neuralnoise.integration.util.CRequest;

@Component
public class RequestHandler {

	@ServiceActivator
	public CAnswer handle(CRequest request) {
		System.out.println("request: " + request);
		
		CAnswer answer = new CAnswer();
		answer.setRequest(request);
		
		List<CEvent> events = Lists.newLinkedList();
		
		CEvent event = new CEvent();
		event.setName("Event1");
		event.setContent("This is a sample Event");
		
		Location location = new Location();
		location.setName("A Street, B");
		location.setPoint(new Point(11.0, 12.0));
		
		event.setLocation(location);
		
		events.add(event);
		
		answer.setEvents(events);
		
		return answer;
	}

}
