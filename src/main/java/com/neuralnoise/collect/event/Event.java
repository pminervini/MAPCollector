package com.neuralnoise.collect.event;

import org.joda.time.DateTime;

import com.neuralnoise.collect.ICollectible;
import com.neuralnoise.integration.geo.Location;

public class Event implements ICollectible {

	private final String name, description, type;
	private final DateTime startDate, endDate;
	private final Location location;
	
	public Event(String name, DateTime startDate, DateTime endDate, String description, String type, Location location) {
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.type = type;
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public DateTime getStartDate() {
		return startDate;
	}

	public DateTime getEndDate() {
		return endDate;
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
	
	@Override
	public String toString() {
		return "Event [name=" + name + ", description=" + description + ", startDate=" + startDate + ", endDate=" + endDate + ", location=" + location + "]";
	}

}
