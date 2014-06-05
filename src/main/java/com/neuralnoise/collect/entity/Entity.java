package com.neuralnoise.collect.entity;

import com.neuralnoise.collect.ICollectible;
import com.neuralnoise.integration.geo.Location;

public class Entity implements ICollectible {

	private final String name, description, type;
	private final Location location;
	
	public Entity(String name, String description, String type, Location location) {
		this.name = name;
		this.description = description;
		this.type = type;
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
	
	public String getType() {
		return type;
	}

	public Location getLocation() {
		return location;
	}

}
