package com.neuralnoise.collect.event.discoveringPuglia.util;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neuralnoise.collect.entity.Entity;
import com.neuralnoise.geo.GeoLocationUtils;
import com.neuralnoise.integration.geo.Location;

public class DiscoveringPugliaEntityParser {
	
	private static final Logger log = LoggerFactory.getLogger(DiscoveringPugliaEntityParser.class);

	private DiscoveringPugliaEntityParser() { }

	public static Entity parse(Map<String, String> map) throws Exception {
		final String category = map.get("type");
		if (category == null)
			return null;
		
		String type = null;
		switch (category) {
		case "arte e cultura": {
			//
		} break;
		case "campagna e sapori": {
			type = "organization";
		} break;
		case "cantine": {
			type = "organization";
		} break;
		case "infopoint": {
			//
		} break;
		case "masserie didattiche": {
			type = "organization";
		} break;
		case "natura e sport": {
			//
		} break;
		case "riti e tradizioni": {
			type = "organization";
		} break;
		default: {
			throw new IllegalArgumentException("Unknown type: " + category);
		}
		}
		
		Location location = null;
		
		final String address = map.get("address");
		if (address != null && type != null) {
			String str = address + ", Puglia";
			List<Location> locations = GeoLocationUtils.query(str, "it");
			
			if (locations.size() > 0) {
				location = locations.get(0);
			}
		}
		
		final String name = map.get("name"), description = map.get("description");
		
		return new Entity(name, description, type, location);
	}
	
}
