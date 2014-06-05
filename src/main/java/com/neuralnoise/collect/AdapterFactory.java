package com.neuralnoise.collect;

import com.neuralnoise.collect.entity.cna.CNAAdapter;
import com.neuralnoise.collect.event.discoveringPuglia.DiscoveringPugliaAdapter;
import com.neuralnoise.collect.event.pugliaEvents.PugliaEventsAdapter;
import com.neuralnoise.collect.event.test.TestAdapter;

public class AdapterFactory {

	private String adapterName;
	private String resource;
	
	public AdapterFactory() { };
	
	public AdapterFactory adapterName(String adapterName)  {
		this.adapterName = adapterName;
		return this;
	}
	
	public AdapterFactory resource(String resource)  {
		this.resource = resource;
		return this;
	}
	
	public IAdapter build() {
		IAdapter adapter = null;
		switch (this.adapterName) {
		case "PugliaEvents": {
			adapter = new PugliaEventsAdapter(resource);
		} break;
		case "CNA": {
			adapter = new CNAAdapter(resource);
		} break;
		case "DiscoveringPuglia": {
			adapter = new DiscoveringPugliaAdapter(resource);
		} break;
		case "Test": {
			adapter = new TestAdapter();
		} break;
		default: {
			throw new IllegalArgumentException("Unknown adapter name: " + this.adapterName);
		}
		}
		return adapter;
	}
	
}
