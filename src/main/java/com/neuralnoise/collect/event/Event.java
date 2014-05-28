package com.neuralnoise.collect.event;

import java.util.Calendar;

import com.neuralnoise.collect.ICollectible;

public class Event implements ICollectible {

	private final String name, description;
	private final Calendar startDate, endDate;
	
	public Event(String name, Calendar startDate, Calendar endDate, String description) {
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
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

}
