package com.neuralnoise.collect.event.test;

import java.util.Collection;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.neuralnoise.collect.IAdapter;
import com.neuralnoise.collect.ICollectible;
import com.neuralnoise.collect.event.Event;

public class TestAdapter implements IAdapter {

	private static final Logger log = LoggerFactory.getLogger(TestAdapter.class);
	
	public TestAdapter() { }
	
	@Override
	public Collection<ICollectible> collect() throws Exception {
		final Collection<ICollectible> events = Lists.newLinkedList();
		DateTime dt = new DateTime();
		Event event = new Event("Test name", dt, dt, "Test content", null);
		events.add(event);
		return events;
	}

}
