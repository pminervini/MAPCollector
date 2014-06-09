package com.neuralnoise.collect.event.discoveringPuglia;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.neuralnoise.collect.IAdapter;
import com.neuralnoise.collect.ICollectible;
import com.neuralnoise.collect.entity.Entity;
import com.neuralnoise.collect.event.discoveringPuglia.util.DiscoveringPugliaEntityParser;
import com.neuralnoise.collect.event.discoveringPuglia.util.DiscoveringPugliaUtils;

public class DiscoveringPugliaAdapter implements IAdapter {

	private static final Logger log = LoggerFactory.getLogger(DiscoveringPugliaAdapter.class);

	private final String resource;
	
	public DiscoveringPugliaAdapter(String resource) {
		this.resource = resource;
	}
	
	@Override
	public Collection<ICollectible> collect() throws Exception {

		List<Map<String, String>> content = DiscoveringPugliaUtils.retrieve(resource);
		
		Collection<ICollectible> entities = Lists.newLinkedList();
		for (Map<String, String> map : content) {
			log.info("Content: " + map);
			Entity entity = DiscoveringPugliaEntityParser.parse(map);
			
			if (entity != null) {
				entities.add(entity);
			}
		}
		
		return entities;
	}
	
}
