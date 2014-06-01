package com.neuralnoise.collect.event;

import java.util.Calendar;

import com.neuralnoise.collect.ICollectible;
import com.neuralnoise.integration.geo.Location;

public class Event implements ICollectible {

	private final String name, description;
	private final Calendar startDate, endDate;
	private final Location location;
	
	public Event(String name, Calendar startDate, Calendar endDate, String description, Location location) {
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public Calendar getStartDate() {
		return startDate;
	}

	public Calendar getEndDate() {
		return endDate;
	}

	public String getDescription() {
		return description;
	}

	public Location getLocation() {
		return location;
	}
	
	@Override
	public String toString() {
		return "Event [name=" + name + ", description=" + description + ", startDate=" + startDate + ", endDate=" + endDate + ", location=" + location + "]";
	}

}
