package com.neuralnoise.util;

import org.springframework.integration.support.json.Jackson2JsonObjectMapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.joda.JodaModule;

public class CustomObjectMapper extends ObjectMapper {

	private static final long serialVersionUID = -2314203051178022125L;

	public CustomObjectMapper() {
		SimpleModule module = new SimpleModule("MAPJSONModule");
		this.registerModule(module);
		this.registerModule(new JodaModule());
	}

	public static Jackson2JsonObjectMapper getMapper() {
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule("MAPJSONModule");
		mapper.registerModule(module);
		mapper.registerModule(new JodaModule());		
		return new Jackson2JsonObjectMapper(mapper);
	}

}
